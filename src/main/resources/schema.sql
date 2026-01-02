-- Database Schema for MS Feedback Service
-- MySQL Database Schema

-- Create database (uncomment if needed)
-- CREATE DATABASE IF NOT EXISTS feedback_db;
-- USE feedback_db;

-- Table: feedback
-- Stores feedback entries from students and teachers
CREATE TABLE IF NOT EXISTS feedback (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(1000) NOT NULL,
    rating INT,
    urgency_type VARCHAR(20) NOT NULL CHECK (urgency_type IN ('LOW', 'MEDIUM', 'HIGH', 'URGENT')),
    sent_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_urgency_type (urgency_type),
    INDEX idx_sent_date (sent_date),
    INDEX idx_rating (rating)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

