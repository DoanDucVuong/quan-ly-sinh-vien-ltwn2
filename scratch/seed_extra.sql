-- ============================================================
-- SEED DATA bổ sung: Phân lớp HP, Cố vấn lớp, Trạng thái SV
-- ============================================================
USE student_management;
GO

-- Fix encoding cho cột status trong student_classe_sections
ALTER TABLE student_classe_sections ALTER COLUMN status NVARCHAR(50);
ALTER TABLE student_classe_sections ALTER COLUMN note NVARCHAR(255);
ALTER TABLE student_status ALTER COLUMN status_code NVARCHAR(50);
ALTER TABLE student_status ALTER COLUMN status_name NVARCHAR(100);
ALTER TABLE student_status ALTER COLUMN description NVARCHAR(255);
ALTER TABLE student_status ALTER COLUMN reason NVARCHAR(255);
ALTER TABLE advisor_classe_sections ALTER COLUMN description NVARCHAR(255);
ALTER TABLE advisor_classe_sections ALTER COLUMN reason NVARCHAR(255);
GO

-- XÓA DỮ LIỆU CŨ
DELETE FROM student_classe_sections;
DELETE FROM advisor_classe_sections;
DELETE FROM student_status;
GO

-- ─────────────────────────────────────────
-- Lấy ID các sinh viên và lớp đã có
-- ─────────────────────────────────────────
DECLARE @sv001 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023001');
DECLARE @sv002 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023002');
DECLARE @sv003 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023003');
DECLARE @sv004 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023004');
DECLARE @sv005 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023005');
DECLARE @sv006 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023006');
DECLARE @sv007 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023007');
DECLARE @sv008 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023008');
DECLARE @sv009 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023009');
DECLARE @sv010 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023010');
DECLARE @sv011 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023011');
DECLARE @sv012 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023012');
DECLARE @sv013 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023013');
DECLARE @sv014 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023014');
DECLARE @sv015 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023015');
DECLARE @sv024 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2024001');
DECLARE @sv025 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2024002');
DECLARE @sv026 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2024003');
DECLARE @sv027 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2024004');
DECLARE @sv028 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2024005');

DECLARE @cls_cntt_a1 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'CNTT-K2023-A1');
DECLARE @cls_cntt_a2 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'CNTT-K2023-A2');
DECLARE @cls_cntt_24 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'CNTT-K2024-A1');
DECLARE @cls_httt_a1 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'HTTT-K2023-A1');
DECLARE @cls_qtkd_a1 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'QTKD-K2023-A1');
DECLARE @cls_kt_a1   UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'KT-K2024-A1');
DECLARE @cls_nn_a1   UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'NN-K2023-A1');

-- ══════════════════════════════════════════════
-- 1. PHÂN LỚP HP (student_classe_sections)
--    Mỗi sinh viên được phân vào lớp học phần
-- ══════════════════════════════════════════════
INSERT INTO student_classe_sections (id, student_id, student_classe_id, status, note, start_date, end_date, is_active, created_at, updated_at)
VALUES
  -- Lớp CNTT-K2023-A1 (5 SV)
  (NEWID(), @sv001, @cls_cntt_a1, N'Đang học', N'Sinh viên đã hoàn thành học phần kỳ 1',          '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv002, @cls_cntt_a1, N'Đang học', N'Sinh viên tham gia đầy đủ',                       '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv003, @cls_cntt_a1, N'Đang học', N'Sinh viên có kết quả học tập tốt',               '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv004, @cls_cntt_a1, N'Đang học', N'Sinh viên năng động, tích cực tham gia',         '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv005, @cls_cntt_a1, N'Đang học', N'Sinh viên có thành tích học tập xuất sắc',       '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  -- Lớp CNTT-K2023-A2 (3 SV)
  (NEWID(), @sv006, @cls_cntt_a2, N'Đang học', N'Sinh viên hoàn thành tốt nhiệm vụ học tập',     '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv007, @cls_cntt_a2, N'Đang học', N'Sinh viên tích cực tham gia hoạt động lớp',     '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv008, @cls_cntt_a2, N'Bảo lưu',  N'Sinh viên bảo lưu kết quả do lý do gia đình',  '2023-09-01', '2024-01-15', 1, GETDATE(), GETDATE()),
  -- Lớp CNTT-K2024-A1 (3 SV)
  (NEWID(), @sv024, @cls_cntt_24, N'Đang học', N'Tân sinh viên nhập học kỳ 2024',                '2024-09-01', '2025-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv025, @cls_cntt_24, N'Đang học', N'Tân sinh viên - kết quả học tập tốt',           '2024-09-01', '2025-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv026, @cls_cntt_24, N'Đang học', N'Tân sinh viên - chăm chỉ và cầu tiến',         '2024-09-01', '2025-06-30', 1, GETDATE(), GETDATE()),
  -- Lớp HTTT-K2023-A1 (3 SV)
  (NEWID(), @sv009, @cls_httt_a1, N'Đang học', N'Sinh viên có năng lực tốt về HTTT',             '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv010, @cls_httt_a1, N'Đang học', N'Sinh viên nổi bật trong các môn chuyên ngành',  '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv011, @cls_httt_a1, N'Tốt nghiệp',N'Sinh viên đã hoàn thành chương trình đào tạo', '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  -- Lớp QTKD-K2023-A1 (3 SV)
  (NEWID(), @sv012, @cls_qtkd_a1, N'Đang học', N'Sinh viên học tốt các môn kinh tế',             '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv013, @cls_qtkd_a1, N'Đang học', N'Sinh viên tham gia đầy đủ các buổi thực hành', '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv014, @cls_qtkd_a1, N'Thôi học', N'Sinh viên nộp đơn xin thôi học ngày 15/03/2024','2023-09-01', '2024-03-15', 1, GETDATE(), GETDATE()),
  -- Lớp KT-K2024-A1 (2 SV)
  (NEWID(), @sv027, @cls_kt_a1,   N'Đang học', N'Tân sinh viên kế toán - chăm chỉ học tập',     '2024-09-01', '2025-06-30', 1, GETDATE(), GETDATE()),
  (NEWID(), @sv028, @cls_kt_a1,   N'Đang học', N'Tân sinh viên kế toán - thành tích xuất sắc',  '2024-09-01', '2025-06-30', 1, GETDATE(), GETDATE()),
  -- Lớp NN-K2023-A1 (1 SV)
  (NEWID(), @sv015, @cls_nn_a1,   N'Đang học', N'Sinh viên tiếng Anh - giao tiếp tốt',           '2023-09-01', '2024-06-30', 1, GETDATE(), GETDATE());
GO

-- ══════════════════════════════════════════════
-- 2. CỐ VẤN LỚP (advisor_classe_sections)
--    Mỗi lớp có 1 giảng viên cố vấn (employee_id là mã GV)
-- ══════════════════════════════════════════════
DECLARE @cls_cntt_a1 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'CNTT-K2023-A1');
DECLARE @cls_cntt_a2 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'CNTT-K2023-A2');
DECLARE @cls_cntt_24 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'CNTT-K2024-A1');
DECLARE @cls_httt_a1 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'HTTT-K2023-A1');
DECLARE @cls_attt_a1 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'ATTT-K2023-A1');
DECLARE @cls_qtkd_a1 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'QTKD-K2023-A1');
DECLARE @cls_kt_a1   UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'KT-K2024-A1');
DECLARE @cls_nn_a1   UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'NN-K2023-A1');

INSERT INTO advisor_classe_sections (id, student_classe_id, employee_id, start_date, end_date, description, reason, is_active, created_at, updated_at)
VALUES
  (NEWID(), @cls_cntt_a1, N'GV001', '2023-09-01', '2026-06-30', N'Thầy Nguyễn Văn Hùng - Bộ môn Kỹ thuật phần mềm',     N'Phân công đầu năm học 2023-2024', 1, GETDATE(), GETDATE()),
  (NEWID(), @cls_cntt_a2, N'GV002', '2023-09-01', '2026-06-30', N'Cô Trần Thị Mai - Bộ môn Hệ thống thông tin',          N'Phân công đầu năm học 2023-2024', 1, GETDATE(), GETDATE()),
  (NEWID(), @cls_cntt_24, N'GV003', '2024-09-01', '2027-06-30', N'Thầy Lê Hoàng Anh - Bộ môn Mạng máy tính',            N'Phân công đầu năm học 2024-2025', 1, GETDATE(), GETDATE()),
  (NEWID(), @cls_httt_a1, N'GV004', '2023-09-01', '2026-06-30', N'Cô Phạm Thị Lan - Bộ môn Hệ thống thông tin',         N'Phân công đầu năm học 2023-2024', 1, GETDATE(), GETDATE()),
  (NEWID(), @cls_attt_a1, N'GV005', '2023-09-01', '2026-06-30', N'Thầy Vũ Minh Tuấn - Bộ môn An toàn thông tin',        N'Phân công đầu năm học 2023-2024', 1, GETDATE(), GETDATE()),
  (NEWID(), @cls_qtkd_a1, N'GV006', '2023-09-01', '2026-06-30', N'Cô Hoàng Thị Thu - Bộ môn Quản trị kinh doanh',       N'Phân công đầu năm học 2023-2024', 1, GETDATE(), GETDATE()),
  (NEWID(), @cls_kt_a1,   N'GV007', '2024-09-01', '2027-06-30', N'Thầy Đặng Quang Minh - Bộ môn Kế toán - Kiểm toán',  N'Phân công đầu năm học 2024-2025', 1, GETDATE(), GETDATE()),
  (NEWID(), @cls_nn_a1,   N'GV008', '2023-09-01', '2026-06-30', N'Cô Nguyễn Thị Hương - Bộ môn Ngoại ngữ',             N'Phân công đầu năm học 2023-2024', 1, GETDATE(), GETDATE());
GO

-- ══════════════════════════════════════════════
-- 3. TRẠNG THÁI SV (student_status)
--    Lịch sử thay đổi trạng thái của sinh viên
-- ══════════════════════════════════════════════
DECLARE @sv001 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023001');
DECLARE @sv002 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023002');
DECLARE @sv003 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023003');
DECLARE @sv004 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023004');
DECLARE @sv005 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023005');
DECLARE @sv006 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023006');
DECLARE @sv007 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023007');
DECLARE @sv008 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023008');
DECLARE @sv009 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023009');
DECLARE @sv010 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023010');
DECLARE @sv011 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023011');
DECLARE @sv012 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023012');
DECLARE @sv013 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023013');
DECLARE @sv014 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023014');
DECLARE @sv015 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2023015');
DECLARE @sv024 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2024001');
DECLARE @sv025 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2024002');
DECLARE @sv026 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2024003');
DECLARE @sv027 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2024004');
DECLARE @sv028 UNIQUEIDENTIFIER = (SELECT id FROM students WHERE code = N'SV2024005');

INSERT INTO student_status (id, student_id, status_code, status_name, start_date, end_date, description, reason, is_active, created_at, updated_at)
VALUES
  -- Sinh viên đang học (17 SV)
  (NEWID(), @sv001, N'DH', N'Đang học',   '2023-09-01', NULL, N'Sinh viên đang theo học kỳ 3, kết quả tốt',          N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv002, N'DH', N'Đang học',   '2023-09-01', NULL, N'Sinh viên chăm chỉ, tham gia đầy đủ các môn học',   N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv003, N'DH', N'Đang học',   '2023-09-01', NULL, N'Học lực khá, có cải thiện rõ rệt từ kỳ 2',          N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv004, N'DH', N'Đang học',   '2023-09-01', NULL, N'Sinh viên năng động, tham gia nhiều hoạt động',      N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv005, N'DH', N'Đang học',   '2023-09-01', NULL, N'Sinh viên xuất sắc, đạt học bổng kỳ 1 và kỳ 2',     N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv006, N'DH', N'Đang học',   '2023-09-01', NULL, N'Sinh viên có tiến bộ tốt trong năm học',             N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv007, N'DH', N'Đang học',   '2023-09-01', NULL, N'Hoàn thành tốt các môn đại cương và chuyên ngành',  N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv009, N'DH', N'Đang học',   '2023-09-01', NULL, N'Sinh viên nghiêm túc, kết quả học tập ổn định',      N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv010, N'DH', N'Đang học',   '2023-09-01', NULL, N'Sinh viên có tư duy logic tốt, phù hợp ngành HTTT', N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv012, N'DH', N'Đang học',   '2023-09-01', NULL, N'Sinh viên học đều các môn, điểm trung bình 7.5',     N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv013, N'DH', N'Đang học',   '2023-09-01', NULL, N'Sinh viên siêng năng, hoàn thành tốt bài tập nhóm', N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv015, N'DH', N'Đang học',   '2023-09-01', NULL, N'Sinh viên có khả năng giao tiếp tiếng Anh tốt',     N'Nhập học đầu năm 2023',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv024, N'DH', N'Đang học',   '2024-09-01', NULL, N'Tân sinh viên K2024, nhập học đúng hạn',             N'Nhập học đầu năm 2024',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv025, N'DH', N'Đang học',   '2024-09-01', NULL, N'Tân sinh viên K2024, điểm đầu vào cao',              N'Nhập học đầu năm 2024',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv026, N'DH', N'Đang học',   '2024-09-01', NULL, N'Tân sinh viên K2024, tích cực hòa nhập lớp học',    N'Nhập học đầu năm 2024',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv027, N'DH', N'Đang học',   '2024-09-01', NULL, N'Tân sinh viên K2024 ngành Kế toán',                  N'Nhập học đầu năm 2024',         1, GETDATE(), GETDATE()),
  (NEWID(), @sv028, N'DH', N'Đang học',   '2024-09-01', NULL, N'Tân sinh viên K2024, điểm thi tuyển sinh xuất sắc', N'Nhập học đầu năm 2024',         1, GETDATE(), GETDATE()),
  -- Sinh viên bảo lưu
  (NEWID(), @sv008, N'BL', N'Bảo lưu',   '2023-09-01', '2024-01-15', N'Sinh viên xin bảo lưu do lý do gia đình',   N'Gia đình có hoàn cảnh khó khăn', 1, GETDATE(), GETDATE()),
  -- Sinh viên tốt nghiệp
  (NEWID(), @sv011, N'TN', N'Tốt nghiệp','2023-09-01', '2024-06-15', N'Sinh viên hoàn thành khóa học đúng hạn',    N'Hoàn thành đủ tín chỉ theo quy định', 1, GETDATE(), GETDATE()),
  -- Sinh viên thôi học
  (NEWID(), @sv014, N'TH', N'Thôi học',  '2023-09-01', '2024-03-15', N'Sinh viên nộp đơn xin thôi học',           N'Chuyển sang trường khác theo nguyện vọng gia đình', 1, GETDATE(), GETDATE());
GO

PRINT N'✅ Seed data bổ sung hoàn tất!';
PRINT N'   - 20 bản ghi Phân lớp HP';
PRINT N'   - 8 bản ghi Cố vấn lớp';
PRINT N'   - 20 bản ghi Trạng thái SV';
GO
