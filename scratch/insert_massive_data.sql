-- 1. Create a temporary table of mock students to insert
CREATE TABLE #MockStudents (
    id INT IDENTITY(1,1),
    FullName NVARCHAR(255),
    Gender NVARCHAR(10),
    BirthDate DATE,
    ClassCode NVARCHAR(50),
    Status NVARCHAR(50)
);

-- 2. Populate mock student details
INSERT INTO #MockStudents (FullName, Gender, BirthDate, ClassCode, Status) VALUES
(N'Nguyễn Văn Hải', N'Nam', '2005-03-12', 'CNTT-K2023-A1', N'Đang học'),
(N'Lê Thị Mai', N'Nữ', '2005-07-24', 'CNTT-K2023-A1', N'Đang học'),
(N'Trần Minh Tuấn', N'Nam', '2005-11-02', 'CNTT-K2023-A1', N'Đang học'),
(N'Phạm Thanh Thảo', N'Nữ', '2005-02-15', 'CNTT-K2023-A1', N'Thôi học'),
(N'Hoàng Quốc Anh', N'Nam', '2005-09-30', 'CNTT-K2023-A1', N'Đang học'),
(N'Phan Thị Kim Oanh', N'Nữ', '2005-05-18', 'CNTT-K2023-A2', N'Đang học'),
(N'Bùi Quang Huy', N'Nam', '2005-01-25', 'CNTT-K2023-A2', N'Bảo lưu'),
(N'Ngô Văn Nam', N'Nam', '2005-08-14', 'CNTT-K2023-A2', N'Đang học'),
(N'Đặng Thu Trang', N'Nữ', '2005-12-10', 'CNTT-K2023-A2', N'Đang học'),
(N'Vũ Hoài Nam', N'Nam', '2005-04-05', 'CNTT-K2023-A2', N'Đang học'),
(N'Nguyễn Hoàng Long', N'Nam', '2005-06-22', 'HTTT-K2023-A1', N'Đang học'),
(N'Trần Thị Hồng', N'Nữ', '2005-10-14', 'HTTT-K2023-A1', N'Đang học'),
(N'Lê Huy Hoàng', N'Nam', '2005-02-28', 'HTTT-K2023-A1', N'Đang học'),
(N'Phạm Minh Đức', N'Nam', '2005-08-08', 'HTTT-K2023-A1', N'Tốt nghiệp'),
(N'Nguyễn Thị Tuyết', N'Nữ', '2005-11-19', 'HTTT-K2023-A1', N'Đang học'),
(N'Bùi Minh Triết', N'Nam', '2005-03-03', 'ATTT-K2023-A1', N'Đang học'),
(N'Võ Hoàng Yến', N'Nữ', '2005-09-12', 'ATTT-K2023-A1', N'Đang học'),
(N'Dương Văn Đạt', N'Nam', '2005-07-07', 'ATTT-K2023-A1', N'Đang học'),
(N'Đỗ Thị Dung', N'Nữ', '2005-05-25', 'ATTT-K2023-A1', N'Bảo lưu'),
(N'Trịnh Công Sơn', N'Nam', '2005-12-31', 'ATTT-K2023-A1', N'Đang học'),
(N'Nguyễn Thanh Hà', N'Nữ', '2005-04-16', 'NN-K2023-A1', N'Đang học'),
(N'Lê Văn Lâm', N'Nam', '2005-10-20', 'NN-K2023-A1', N'Đang học'),
(N'Trần Đức Bo', N'Nam', '2005-01-01', 'NN-K2023-A1', N'Thôi học'),
(N'Phạm Hồng Nhung', N'Nữ', '2005-08-27', 'NN-K2023-A1', N'Đang học'),
(N'Hoàng Văn Thái', N'Nam', '2005-06-06', 'NN-K2023-A1', N'Tốt nghiệp'),
(N'Nguyễn Hoài Bảo', N'Nam', '2006-02-14', 'KT-K2024-A1', N'Đang học'),
(N'Đinh Thị Xuân', N'Nữ', '2006-03-20', 'KT-K2024-A1', N'Đang học'),
(N'Lê Quốc Khánh', N'Nam', '2006-09-09', 'KT-K2024-A1', N'Đang học'),
(N'Vũ Thị Hương', N'Nữ', '2006-11-11', 'KT-K2024-A1', N'Đang học'),
(N'Trần Thanh Tâm', N'Nữ', '2006-05-05', 'KT-K2024-A1', N'Đang học');

-- 3. Loop and insert into students table
DECLARE @i INT = 1;
DECLARE @Total INT = (SELECT COUNT(*) FROM #MockStudents);

DECLARE @FullName NVARCHAR(255);
DECLARE @Gender NVARCHAR(10);
DECLARE @BirthDate DATE;
DECLARE @ClassCode NVARCHAR(50);
DECLARE @Status NVARCHAR(50);

DECLARE @ClassId UNIQUEIDENTIFIER;
DECLARE @DeptId UNIQUEIDENTIFIER;
DECLARE @MajorId UNIQUEIDENTIFIER;
DECLARE @AcadYearId UNIQUEIDENTIFIER;
DECLARE @TpId UNIQUEIDENTIFIER;

DECLARE @NewStudentId UNIQUEIDENTIFIER;
DECLARE @StudentCode VARCHAR(100);
DECLARE @BaseSeq INT = 20; -- Start sequence for newly generated SV codes

WHILE @i <= @Total
BEGIN
    SELECT 
        @FullName = FullName, 
        @Gender = Gender, 
        @BirthDate = BirthDate, 
        @ClassCode = ClassCode, 
        @Status = Status
    FROM #MockStudents WHERE id = @i;

    -- Get Class & its related IDs
    SELECT 
        @ClassId = id,
        @DeptId = department_id,
        @MajorId = major_id,
        @AcadYearId = academic_year_id
    FROM student_classes 
    WHERE code = @ClassCode;

    -- Fallbacks
    IF @DeptId IS NULL 
        SELECT TOP 1 @DeptId = id FROM departments;
    IF @MajorId IS NULL 
        SELECT TOP 1 @MajorId = id FROM majors;
    IF @AcadYearId IS NULL 
        SELECT TOP 1 @AcadYearId = id FROM academic_years;

    -- Find a matching training program based on Major
    SELECT TOP 1 @TpId = id FROM training_programs WHERE major_id = @MajorId;
    IF @TpId IS NULL
        SELECT TOP 1 @TpId = id FROM training_programs;

    -- Generate a unique Student Code sequentially
    -- For example: SV2023020, SV2023021, etc.
    DECLARE @YearStr VARCHAR(4) = CASE WHEN @ClassCode LIKE '%2024%' THEN '2024' ELSE '2023' END;
    SET @StudentCode = 'SV' + @YearStr + RIGHT('000' + CAST((@BaseSeq + @i) AS VARCHAR), 3);

    -- Generate Student ID
    SET @NewStudentId = NEWID();

    -- Insert into students
    INSERT INTO students (
        id, code, full_name, date_of_birth, gender, 
        personal_identification_number, date_of_issue, card_place, 
        address, current_address, academic_year_year, department_id, 
        major_id, training_program_id, status, student_classe_id, 
        admission_year, created_at, is_active
    ) VALUES (
        @NewStudentId, @StudentCode, @FullName, @BirthDate, @Gender,
        '037' + RIGHT('000000000' + CAST(CAST(RAND() * 1000000000 AS BIGINT) AS VARCHAR), 9), 
        DATEADD(year, -2, GETDATE()), N'Cục Cảnh sát QLHC về trật tự xã hội',
        N'Hà Nội, Việt Nam', N'Hà Nội, Việt Nam', @AcadYearId, @DeptId,
        @MajorId, @TpId, @Status, @ClassId,
        GETDATE(), GETDATE(), 1
    );

    -- Insert into student_status table as history record
    INSERT INTO student_status (
        id, student_id, status_code, status_name, start_date, 
        description, created_at, is_active
    ) VALUES (
        NEWID(), @NewStudentId, 
        CASE 
            WHEN @Status = N'Đang học' THEN 'DH'
            WHEN @Status = N'Thôi học' THEN 'TH'
            WHEN @Status = N'Tốt nghiệp' THEN 'TN'
            ELSE 'BL'
        END,
        @Status, GETDATE(), 
        N'Cập nhật trạng thái nhập học ban đầu', GETDATE(), 1
    );

    -- Insert several student class sections (phân lớp học phần)
    -- For demonstration, assign each student to 2 different random class sections or class sections related to their class
    INSERT INTO student_classe_sections (
        id, student_id, student_classe_id, status, note, created_at, is_active
    ) VALUES (
        NEWID(), @NewStudentId, @ClassId, @Status, N'Phân lớp tự động hệ thống', GETDATE(), 1
    );

    SET @i = @i + 1;
END;

-- Clean up
DROP TABLE #MockStudents;

PRINT 'Successfully populated 30 grand mock students and synced their statuses!';
