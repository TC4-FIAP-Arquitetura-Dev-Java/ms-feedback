package com.ms.feedback.entrypoint.controllers;

import com.ms.feedback.application.dto.FeedbackFilter;
import com.ms.feedback.application.usecase.CreateFeedbackUseCase;
import com.ms.feedback.application.usecase.DeleteFeedbackUseCase;
import com.ms.feedback.application.usecase.GetFeedbackByIdUseCase;
import com.ms.feedback.application.usecase.GetFeedbackReportUseCase;
import com.ms.feedback.application.usecase.ListFeedbackUseCase;
import com.ms.feedback.application.usecase.UpdateFeedbackUseCase;
import com.ms.feedback.domain.enuns.UrgencyTypeEnum;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.domain.model.FeedbackReportDomain;
import com.ms.feedback.entrypoint.controllers.mappers.FeedbackDtoMapper;
import com.ms.feedback.entrypoint.controllers.presenter.FeedbackPresenter;
import com.ms.feedback.infrastructure.config.security.SecurityUtil;
import com.ms.loginDomain.FeedbackApi;
import com.ms.loginDomain.gen.model.DateRequestDto;
import com.ms.loginDomain.gen.model.FeedbackReportResponseDto;
import com.ms.loginDomain.gen.model.FeedbackRequestDto;
import com.ms.loginDomain.gen.model.FeedbackResponseDto;
import com.ms.loginDomain.gen.model.PagedFeedbackResponseDto;
import com.ms.loginDomain.gen.model.UrgencyTypeEnumDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1")
public class FeedbackController implements FeedbackApi {

    private final GetFeedbackByIdUseCase getFeedbackByIdUseCase;
    private final ListFeedbackUseCase listFeedbackUseCase;
    private final CreateFeedbackUseCase createFeedbackUseCase;
    private final DeleteFeedbackUseCase deleteFeedbackUseCase;
    private final UpdateFeedbackUseCase updateFeedbackUseCase;
    private final GetFeedbackReportUseCase getFeedbackReportUseCase;

    private final FeedbackDtoMapper feedbackDtoMapper;

    public FeedbackController(GetFeedbackByIdUseCase getFeedbackByIdUseCase,
                              ListFeedbackUseCase listFeedbackUseCase,
                              CreateFeedbackUseCase createFeedbackUseCase,
                              DeleteFeedbackUseCase deleteFeedbackUseCase,
                              UpdateFeedbackUseCase updateFeedbackUseCase,
                              FeedbackDtoMapper feedbackDtoMapper,
                              SecurityUtil securityUtil,
                              GetFeedbackReportUseCase getFeedbackReportUseCase) {
        this.getFeedbackByIdUseCase = getFeedbackByIdUseCase;
        this.listFeedbackUseCase = listFeedbackUseCase;
        this.createFeedbackUseCase = createFeedbackUseCase;
        this.deleteFeedbackUseCase = deleteFeedbackUseCase;
        this.updateFeedbackUseCase = updateFeedbackUseCase;
        this.feedbackDtoMapper = feedbackDtoMapper;
        this.getFeedbackReportUseCase = getFeedbackReportUseCase;
    }

    @Override
    public ResponseEntity<FeedbackResponseDto> _getFeedbackById(String id) {
        FeedbackDomain feedbackDomain = getFeedbackByIdUseCase.getById(id);
        FeedbackResponseDto responseDto = FeedbackDtoMapper.INSTANCE.toFeedbackResponseDto(feedbackDomain);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<PagedFeedbackResponseDto> _listFeedbacks(String description, UrgencyTypeEnumDto urgencyType, Integer limit, Integer offset) {

        int safeLimit = (limit == null || limit <= 0) ? 10 : limit;
        int safeOffset = (offset == null || offset <= 0) ? 0 : offset;

        int page = safeOffset / safeLimit;

        Pageable pageable = PageRequest.of(page, safeLimit);
        UrgencyTypeEnum type = feedbackDtoMapper.toUrgencyTypeEnum(urgencyType);
        FeedbackFilter filter = new FeedbackFilter(description, type);

        Page<FeedbackDomain> domainPage = listFeedbackUseCase.findAll(filter, pageable);
        List<FeedbackResponseDto> content = feedbackDtoMapper.toListFeedbackResponseDto(domainPage.getContent());

        PagedFeedbackResponseDto response = new PagedFeedbackResponseDto();
        response.setContent(content);
        response.setLimit(safeLimit);
        response.setOffset(safeOffset);
        response.setTotalElements(domainPage.getTotalElements());

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> _createFeedback(FeedbackRequestDto feedbackRequestDto) {
        FeedbackDomain feedbackDomain = FeedbackPresenter.toFeedbackDomain(feedbackRequestDto);
        createFeedbackUseCase.create(feedbackDomain);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> _updateFeedback(String id, FeedbackRequestDto feedbackRequestDto) {
        FeedbackDomain domainUpdate = FeedbackPresenter.toFeedbackDomain(feedbackRequestDto);
        updateFeedbackUseCase.update(id, domainUpdate);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> _deleteFeedback(String id) {
        deleteFeedbackUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<FeedbackReportResponseDto> _report(DateRequestDto dateRequestDto) {
        FeedbackReportDomain feedbackDomain = getFeedbackReportUseCase.getFeedbackReportWeek(dateRequestDto.getDate());
        FeedbackReportResponseDto responseDto = FeedbackDtoMapper.INSTANCE.toFeedbackReportResponseDto(feedbackDomain);
        return ResponseEntity.ok(responseDto);
    }

    @org.springframework.web.bind.annotation.GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}
