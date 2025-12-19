package com.ms.feedback.entrypoint.controllers;

import com.ms.feedback.application.usecase.*;
import com.ms.feedback.domain.model.FeedbackDomain;
import com.ms.feedback.entrypoint.controllers.filter.FeedbackFilter;
import com.ms.feedback.entrypoint.controllers.mappers.FeedbackDtoMapper;
import com.ms.feedback.entrypoint.controllers.mappers.FeedbackFilterMapper;
import com.ms.feedback.entrypoint.controllers.presenter.FeedbackPresenter;
import com.ms.loginDomain.FeedbackApi;
import com.ms.loginDomain.gen.model.FeedbackRequestDto;
import com.ms.loginDomain.gen.model.FeedbackResponseDto;
import com.ms.loginDomain.gen.model.TipoUrgenciaEnumDto;
import lombok.extern.slf4j.Slf4j;
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

    private final FeedbackFilterMapper feedbackFilterMapper;
    private final FeedbackDtoMapper feedbackDtoMapper;

    public FeedbackController(GetFeedbackByIdUseCase getFeedbackByIdUseCase,
                              ListFeedbackUseCase listFeedbackUseCase,
                              CreateFeedbackUseCase createFeedbackUseCase,
                              DeleteFeedbackUseCase deleteFeedbackUseCase,
                              UpdateFeedbackUseCase updateFeedbackUseCase,
                              FeedbackFilterMapper feedbackFilterMapper,
                              FeedbackDtoMapper  feedbackDtoMapper) {
        this.getFeedbackByIdUseCase = getFeedbackByIdUseCase;
        this.listFeedbackUseCase = listFeedbackUseCase;
        this.createFeedbackUseCase = createFeedbackUseCase;
        this.deleteFeedbackUseCase = deleteFeedbackUseCase;
        this.updateFeedbackUseCase = updateFeedbackUseCase;
        this.feedbackFilterMapper = feedbackFilterMapper;
        this.feedbackDtoMapper = feedbackDtoMapper;
    }

    @Override
    public ResponseEntity<FeedbackResponseDto> _getFeedbackById(String id) {
        FeedbackDomain feedbackDomain = getFeedbackByIdUseCase.getById(id);
        FeedbackResponseDto responseDto = FeedbackDtoMapper.INSTANCE.toFeedbackResponseDto(feedbackDomain);
        return ResponseEntity.ok(responseDto);
    }

    @Override
    public ResponseEntity<List<FeedbackResponseDto>> _listFeedbacks(String descricao, TipoUrgenciaEnumDto tipoUrgencia, Integer limit, Integer offset) {
        FeedbackFilter filter = feedbackFilterMapper.toFilter(descricao, tipoUrgencia, limit, offset);
        List<FeedbackDomain> domains = listFeedbackUseCase.findAll(filter);
        List<FeedbackResponseDto> response = feedbackDtoMapper.toListFeedbackResponseDto(domains);
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
}
