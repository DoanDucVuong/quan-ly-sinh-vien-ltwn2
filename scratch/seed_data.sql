-- ============================================================
-- SEED DATA - Hệ thống Quản lý Sinh viên
-- Chạy trong: SQL Server Management Studio
-- Database: student_management
-- ============================================================
USE student_management;
GO

-- ─────────────────────────────────────────
-- XÓA DỮ LIỆU CŨ (thứ tự: bảng con trước)
-- ─────────────────────────────────────────
DELETE FROM students;
DELETE FROM student_classes;
DELETE FROM training_programs;
DELETE FROM majors;
DELETE FROM departments;
DELETE FROM academic_years;
GO

-- ─────────────────────────────────────────
-- 1. ACADEMIC YEARS
-- ─────────────────────────────────────────
INSERT INTO academic_years (id, code, name, start_year, end_year, is_active, created_at, updated_at) VALUES
  (NEWID(), N'NH2021-2022', N'Năm học 2021-2022', 2021, 2022, 1, GETDATE(), GETDATE()),
  (NEWID(), N'NH2022-2023', N'Năm học 2022-2023', 2022, 2023, 1, GETDATE(), GETDATE()),
  (NEWID(), N'NH2023-2024', N'Năm học 2023-2024', 2023, 2024, 1, GETDATE(), GETDATE()),
  (NEWID(), N'NH2024-2025', N'Năm học 2024-2025', 2024, 2025, 1, GETDATE(), GETDATE());
GO

-- ─────────────────────────────────────────
-- 2. DEPARTMENTS
-- ─────────────────────────────────────────
INSERT INTO departments (id, code, name, description, is_active, created_at, updated_at) VALUES
  (NEWID(), N'CNTT', N'Công nghệ thông tin',    N'Đào tạo về lập trình, mạng máy tính, CNTT',     1, GETDATE(), GETDATE()),
  (NEWID(), N'KT',   N'Kinh tế',                N'Đào tạo về kinh tế, tài chính, quản trị',        1, GETDATE(), GETDATE()),
  (NEWID(), N'NN',   N'Ngoại ngữ',              N'Đào tạo tiếng Anh, tiếng Trung, tiếng Nhật',     1, GETDATE(), GETDATE()),
  (NEWID(), N'CK',   N'Cơ khí',                 N'Đào tạo cơ khí, chế tạo máy, tự động hóa',      1, GETDATE(), GETDATE()),
  (NEWID(), N'XD',   N'Xây dựng',               N'Đào tạo kỹ thuật xây dựng, kiến trúc',           1, GETDATE(), GETDATE());
GO

-- ─────────────────────────────────────────
-- 3. MAJORS (có department_id bắt buộc)
-- ─────────────────────────────────────────
DECLARE @dept_cntt UNIQUEIDENTIFIER = (SELECT id FROM departments WHERE code = N'CNTT');
DECLARE @dept_kt   UNIQUEIDENTIFIER = (SELECT id FROM departments WHERE code = N'KT');
DECLARE @dept_nn   UNIQUEIDENTIFIER = (SELECT id FROM departments WHERE code = N'NN');
DECLARE @dept_ck   UNIQUEIDENTIFIER = (SELECT id FROM departments WHERE code = N'CK');

INSERT INTO majors (id, code, name, department_id, is_active, created_at, updated_at) VALUES
  (NEWID(), N'CNPM',  N'Công nghệ phần mềm',        @dept_cntt, 1, GETDATE(), GETDATE()),
  (NEWID(), N'HTTT',  N'Hệ thống thông tin',        @dept_cntt, 1, GETDATE(), GETDATE()),
  (NEWID(), N'ATTT',  N'An toàn thông tin',          @dept_cntt, 1, GETDATE(), GETDATE()),
  (NEWID(), N'QTKD',  N'Quản trị kinh doanh',       @dept_kt,   1, GETDATE(), GETDATE()),
  (NEWID(), N'KTOAN', N'Kế toán',                   @dept_kt,   1, GETDATE(), GETDATE()),
  (NEWID(), N'TCAN',  N'Tiếng Anh',                 @dept_nn,   1, GETDATE(), GETDATE()),
  (NEWID(), N'CDTO',  N'Cơ điện tử',                @dept_ck,   1, GETDATE(), GETDATE());
GO

-- ─────────────────────────────────────────
-- 4. TRAINING PROGRAMS (có major_id bắt buộc)
-- ─────────────────────────────────────────
DECLARE @major_cnpm UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'CNPM');
DECLARE @major_httt UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'HTTT');
DECLARE @major_qtkd UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'QTKD');
DECLARE @major_ktoan UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'KTOAN');
DECLARE @major_tcan UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'TCAN');

INSERT INTO training_programs (id, code, name, major_id, degree_level, is_active, created_at, updated_at) VALUES
  (NEWID(), N'CQ-CNPM',  N'Chính quy - Công nghệ phần mềm',   @major_cnpm,  N'Đại học', 1, GETDATE(), GETDATE()),
  (NEWID(), N'CQ-HTTT',  N'Chính quy - Hệ thống thông tin',   @major_httt,  N'Đại học', 1, GETDATE(), GETDATE()),
  (NEWID(), N'CQ-QTKD',  N'Chính quy - Quản trị kinh doanh',  @major_qtkd,  N'Đại học', 1, GETDATE(), GETDATE()),
  (NEWID(), N'CQ-KTOAN', N'Chính quy - Kế toán',              @major_ktoan, N'Đại học', 1, GETDATE(), GETDATE()),
  (NEWID(), N'CQ-TCAN',  N'Chính quy - Tiếng Anh',            @major_tcan,  N'Đại học', 1, GETDATE(), GETDATE());
GO

-- ─────────────────────────────────────────
-- 5. STUDENT CLASSES (Lớp hành chính)
-- ─────────────────────────────────────────
DECLARE @dept_cntt  UNIQUEIDENTIFIER = (SELECT id FROM departments WHERE code = N'CNTT');
DECLARE @dept_kt    UNIQUEIDENTIFIER = (SELECT id FROM departments WHERE code = N'KT');
DECLARE @dept_nn    UNIQUEIDENTIFIER = (SELECT id FROM departments WHERE code = N'NN');
DECLARE @major_cnpm UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'CNPM');
DECLARE @major_httt UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'HTTT');
DECLARE @major_attt UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'ATTT');
DECLARE @major_qtkd UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'QTKD');
DECLARE @major_ktoan UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'KTOAN');
DECLARE @major_tcan UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'TCAN');
DECLARE @prog_cnpm  UNIQUEIDENTIFIER = (SELECT id FROM training_programs WHERE code = N'CQ-CNPM');
DECLARE @prog_httt  UNIQUEIDENTIFIER = (SELECT id FROM training_programs WHERE code = N'CQ-HTTT');
DECLARE @prog_qtkd  UNIQUEIDENTIFIER = (SELECT id FROM training_programs WHERE code = N'CQ-QTKD');
DECLARE @prog_ktoan UNIQUEIDENTIFIER = (SELECT id FROM training_programs WHERE code = N'CQ-KTOAN');
DECLARE @prog_tcan  UNIQUEIDENTIFIER = (SELECT id FROM training_programs WHERE code = N'CQ-TCAN');
DECLARE @ay_2023    UNIQUEIDENTIFIER = (SELECT id FROM academic_years WHERE code = N'NH2023-2024');
DECLARE @ay_2024    UNIQUEIDENTIFIER = (SELECT id FROM academic_years WHERE code = N'NH2024-2025');

INSERT INTO student_classes (id, code, name, department_id, major_id, training_program_id, academic_year_id, is_active, created_at, updated_at) VALUES
  (NEWID(), N'CNTT-K2023-A1', N'Công nghệ thông tin K2023 A1', @dept_cntt, @major_cnpm, @prog_cnpm, @ay_2023, 1, GETDATE(), GETDATE()),
  (NEWID(), N'CNTT-K2023-A2', N'Công nghệ thông tin K2023 A2', @dept_cntt, @major_cnpm, @prog_cnpm, @ay_2023, 1, GETDATE(), GETDATE()),
  (NEWID(), N'CNTT-K2024-A1', N'Công nghệ thông tin K2024 A1', @dept_cntt, @major_cnpm, @prog_cnpm, @ay_2024, 1, GETDATE(), GETDATE()),
  (NEWID(), N'HTTT-K2023-A1', N'Hệ thống thông tin K2023 A1', @dept_cntt, @major_httt, @prog_httt, @ay_2023, 1, GETDATE(), GETDATE()),
  (NEWID(), N'ATTT-K2023-A1', N'An toàn thông tin K2023 A1',   @dept_cntt, @major_attt, @prog_cnpm, @ay_2023, 1, GETDATE(), GETDATE()),
  (NEWID(), N'QTKD-K2023-A1', N'Quản trị kinh doanh K2023 A1',@dept_kt,   @major_qtkd, @prog_qtkd, @ay_2023, 1, GETDATE(), GETDATE()),
  (NEWID(), N'KT-K2024-A1',   N'Kế toán K2024 A1',            @dept_kt,   @major_ktoan,@prog_ktoan,@ay_2024, 1, GETDATE(), GETDATE()),
  (NEWID(), N'NN-K2023-A1',   N'Tiếng Anh K2023 A1',          @dept_nn,   @major_tcan, @prog_tcan, @ay_2023, 1, GETDATE(), GETDATE());
GO

-- ─────────────────────────────────────────
-- 6. STUDENTS (20 sinh viên mẫu)
-- ─────────────────────────────────────────
DECLARE @dept_cntt   UNIQUEIDENTIFIER = (SELECT id FROM departments WHERE code = N'CNTT');
DECLARE @dept_kt     UNIQUEIDENTIFIER = (SELECT id FROM departments WHERE code = N'KT');
DECLARE @dept_nn     UNIQUEIDENTIFIER = (SELECT id FROM departments WHERE code = N'NN');
DECLARE @major_cnpm  UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'CNPM');
DECLARE @major_httt  UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'HTTT');
DECLARE @major_qtkd  UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'QTKD');
DECLARE @major_ktoan UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'KTOAN');
DECLARE @major_tcan  UNIQUEIDENTIFIER = (SELECT id FROM majors WHERE code = N'TCAN');
DECLARE @prog_cnpm   UNIQUEIDENTIFIER = (SELECT id FROM training_programs WHERE code = N'CQ-CNPM');
DECLARE @prog_httt   UNIQUEIDENTIFIER = (SELECT id FROM training_programs WHERE code = N'CQ-HTTT');
DECLARE @prog_qtkd   UNIQUEIDENTIFIER = (SELECT id FROM training_programs WHERE code = N'CQ-QTKD');
DECLARE @prog_ktoan  UNIQUEIDENTIFIER = (SELECT id FROM training_programs WHERE code = N'CQ-KTOAN');
DECLARE @prog_tcan   UNIQUEIDENTIFIER = (SELECT id FROM training_programs WHERE code = N'CQ-TCAN');
DECLARE @ay_2023     UNIQUEIDENTIFIER = (SELECT id FROM academic_years WHERE code = N'NH2023-2024');
DECLARE @ay_2024     UNIQUEIDENTIFIER = (SELECT id FROM academic_years WHERE code = N'NH2024-2025');
DECLARE @cls_cntt_a1 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'CNTT-K2023-A1');
DECLARE @cls_cntt_a2 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'CNTT-K2023-A2');
DECLARE @cls_cntt_24 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'CNTT-K2024-A1');
DECLARE @cls_httt_a1 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'HTTT-K2023-A1');
DECLARE @cls_qtkd_a1 UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'QTKD-K2023-A1');
DECLARE @cls_kt_a1   UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'KT-K2024-A1');
DECLARE @cls_nn_a1   UNIQUEIDENTIFIER = (SELECT id FROM student_classes WHERE code = N'NN-K2023-A1');

INSERT INTO students (id, code, full_name, date_of_birth, gender, address, current_address,
                      department_id, major_id, training_program_id, student_classe_id,
                      academic_year_year, status, is_active, created_at, updated_at)
VALUES
  -- Lớp CNTT-K2023-A1
  (NEWID(), N'SV2023001', N'Nguyễn Văn An',       '2004-03-15', N'Nam', N'123 Lê Lợi, Hà Nội',           N'KTX Bách Khoa, Hà Nội',           @dept_cntt, @major_cnpm, @prog_cnpm, @cls_cntt_a1, @ay_2023, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2023002', N'Trần Thị Bích',        '2004-07-22', N'Nữ',  N'45 Trần Hưng Đạo, Hà Nội',     N'Phòng 205, KTX A, Hà Nội',        @dept_cntt, @major_cnpm, @prog_cnpm, @cls_cntt_a1, @ay_2023, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2023003', N'Phạm Minh Châu',       '2004-11-08', N'Nam', N'78 Nguyễn Trãi, Hà Nội',       N'Số 12 ngõ 8 Tạ Quang Bửu, HN',    @dept_cntt, @major_cnpm, @prog_cnpm, @cls_cntt_a1, @ay_2023, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2023004', N'Lê Hoàng Dũng',        '2003-05-30', N'Nam', N'56 Hoàng Diệu, Đà Nẵng',       N'KTX Bách Khoa phòng 301',          @dept_cntt, @major_cnpm, @prog_cnpm, @cls_cntt_a1, @ay_2023, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2023005', N'Nguyễn Thị Hoa',       '2004-02-14', N'Nữ',  N'90 Lý Thường Kiệt, Huế',       N'Phòng 102, KTX Nữ Bách Khoa',     @dept_cntt, @major_cnpm, @prog_cnpm, @cls_cntt_a1, @ay_2023, N'Đang học',   1, GETDATE(), GETDATE()),
  -- Lớp CNTT-K2023-A2
  (NEWID(), N'SV2023006', N'Vũ Quốc Khánh',        '2004-09-18', N'Nam', N'34 Đinh Tiên Hoàng, TP HCM',   N'Ký túc xá ĐH Bách Khoa HCM',      @dept_cntt, @major_cnpm, @prog_cnpm, @cls_cntt_a2, @ay_2023, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2023007', N'Hoàng Thị Lan',        '2004-12-03', N'Nữ',  N'67 Pasteur, TP HCM',           N'214 Lý Thường Kiệt, Quận 10, HCM', @dept_cntt, @major_cnpm, @prog_cnpm, @cls_cntt_a2, @ay_2023, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2023008', N'Đặng Văn Minh',        '2003-06-25', N'Nam', N'12 Bùi Thị Xuân, Bình Dương',  N'KTX Bách Khoa phòng 215',          @dept_cntt, @major_cnpm, @prog_cnpm, @cls_cntt_a2, @ay_2023, N'Bảo lưu',    1, GETDATE(), GETDATE()),
  -- Lớp CNTT-K2024-A1
  (NEWID(), N'SV2024001', N'Trịnh Quang Nam',       '2005-04-12', N'Nam', N'88 Phan Đình Phùng, Hà Nội',   N'KTX sinh viên Mỹ Đình, Hà Nội',   @dept_cntt, @major_cnpm, @prog_cnpm, @cls_cntt_24, @ay_2024, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2024002', N'Ngô Thị Oanh',          '2005-08-29', N'Nữ',  N'15 Trường Chinh, Hà Nội',      N'Phòng 318, KTX Đại học TN',        @dept_cntt, @major_cnpm, @prog_cnpm, @cls_cntt_24, @ay_2024, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2024003', N'Bùi Hữu Phúc',          '2005-01-17', N'Nam', N'203 Nguyễn Huệ, Cần Thơ',     N'KTX Cần Thơ phòng 412',            @dept_cntt, @major_cnpm, @prog_cnpm, @cls_cntt_24, @ay_2024, N'Đang học',   1, GETDATE(), GETDATE()),
  -- Lớp HTTT-K2023-A1
  (NEWID(), N'SV2023009', N'Phan Thị Quỳnh',        '2004-10-05', N'Nữ',  N'5 Lê Duẩn, Hà Nội',           N'Số 7 Tạ Quang Bửu, Hai Bà Trưng',  @dept_cntt, @major_httt, @prog_httt, @cls_httt_a1, @ay_2023, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2023010', N'Cao Quang Sáng',         '2004-03-21', N'Nam', N'102 Nguyễn Văn Cừ, TP HCM',   N'KTX Đại học Quốc gia HCM',         @dept_cntt, @major_httt, @prog_httt, @cls_httt_a1, @ay_2023, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2023011', N'Lý Thị Thanh',           '2003-07-14', N'Nữ',  N'250 Trần Phú, Nha Trang',     N'KTX Đại học Nha Trang',            @dept_cntt, @major_httt, @prog_httt, @cls_httt_a1, @ay_2023, N'Tốt nghiệp', 1, GETDATE(), GETDATE()),
  -- Lớp QTKD-K2023-A1
  (NEWID(), N'SV2023012', N'Đinh Công Uy',           '2004-05-09', N'Nam', N'77 Hùng Vương, Hải Phòng',     N'KTX Đại học Hải Phòng',            @dept_kt,   @major_qtkd, @prog_qtkd, @cls_qtkd_a1, @ay_2023, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2023013', N'Phùng Thị Vân',          '2004-11-30', N'Nữ',  N'33 Lê Lợi, Đà Lạt',           N'Trọ 15/5 Trần Quý Cáp, Đà Lạt',   @dept_kt,   @major_qtkd, @prog_qtkd, @cls_qtkd_a1, @ay_2023, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2023014', N'Lưu Anh Việt',           '2003-08-16', N'Nam', N'92 Nguyễn Thái Học, Hà Nội',  N'KTX Đại học Kinh tế Quốc dân',     @dept_kt,   @major_qtkd, @prog_qtkd, @cls_qtkd_a1, @ay_2023, N'Thôi học',   1, GETDATE(), GETDATE()),
  -- Lớp KT-K2024-A1
  (NEWID(), N'SV2024004', N'Trần Minh Xuân',         '2005-02-28', N'Nam', N'18 Đinh Bộ Lĩnh, Hà Nội',     N'KTX Đại học Thương Mại',           @dept_kt,   @major_ktoan,@prog_ktoan,@cls_kt_a1,  @ay_2024, N'Đang học',   1, GETDATE(), GETDATE()),
  (NEWID(), N'SV2024005', N'Nguyễn Thị Yến',         '2005-06-11', N'Nữ',  N'44 Ngô Quyền, Hà Nội',        N'Phòng 501, KTX B Thương Mại',      @dept_kt,   @major_ktoan,@prog_ktoan,@cls_kt_a1,  @ay_2024, N'Đang học',   1, GETDATE(), GETDATE()),
  -- Lớp NN-K2023-A1
  (NEWID(), N'SV2023015', N'Đoàn Thị Ánh',           '2004-04-07', N'Nữ',  N'29 Bà Triệu, Hà Nội',         N'KTX Đại học Ngoại ngữ Hà Nội',     @dept_nn,   @major_tcan, @prog_tcan, @cls_nn_a1,  @ay_2023, N'Đang học',   1, GETDATE(), GETDATE());
GO

PRINT N'✅ Seed data hoàn tất!';
PRINT N'   Đã thêm: 4 năm học, 5 khoa, 7 ngành, 5 CTĐT, 8 lớp, 20 sinh viên';
GO
