-- Create Master Data Tables
USE student_management;
GO

-- 1. Academic Years
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'academic_years')
BEGIN
    CREATE TABLE academic_years (
        id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
        code NVARCHAR(50) NOT NULL UNIQUE,
        name NVARCHAR(255) NOT NULL,
        start_year INT,
        end_year INT,
        is_active BIT DEFAULT 1,
        created_at DATETIME2 DEFAULT GETDATE(),
        updated_at DATETIME2 DEFAULT GETDATE(),
        deleted_at DATETIME2,
        created_by UNIQUEIDENTIFIER,
        updated_by UNIQUEIDENTIFIER,
        deleted_by UNIQUEIDENTIFIER
    );
    PRINT 'Created academic_years table';
END

-- 2. Departments
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'departments')
BEGIN
    CREATE TABLE departments (
        id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
        code NVARCHAR(50) NOT NULL UNIQUE,
        name NVARCHAR(255) NOT NULL,
        description NVARCHAR(MAX),
        is_active BIT DEFAULT 1,
        created_at DATETIME2 DEFAULT GETDATE(),
        updated_at DATETIME2 DEFAULT GETDATE(),
        deleted_at DATETIME2,
        created_by UNIQUEIDENTIFIER,
        updated_by UNIQUEIDENTIFIER,
        deleted_by UNIQUEIDENTIFIER
    );
    PRINT 'Created departments table';
END

-- 3. Majors
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'majors')
BEGIN
    CREATE TABLE majors (
        id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
        code NVARCHAR(50) NOT NULL UNIQUE,
        name NVARCHAR(255) NOT NULL,
        department_id UNIQUEIDENTIFIER NOT NULL,
        is_active BIT DEFAULT 1,
        created_at DATETIME2 DEFAULT GETDATE(),
        updated_at DATETIME2 DEFAULT GETDATE(),
        deleted_at DATETIME2,
        created_by UNIQUEIDENTIFIER,
        updated_by UNIQUEIDENTIFIER,
        deleted_by UNIQUEIDENTIFIER,
        CONSTRAINT FK_Majors_Departments FOREIGN KEY (department_id) REFERENCES departments(id)
    );
    PRINT 'Created majors table';
END

-- 4. Training Programs
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'training_programs')
BEGIN
    CREATE TABLE training_programs (
        id UNIQUEIDENTIFIER PRIMARY KEY DEFAULT NEWID(),
        code NVARCHAR(50) NOT NULL UNIQUE,
        name NVARCHAR(255) NOT NULL,
        major_id UNIQUEIDENTIFIER NOT NULL,
        degree_level NVARCHAR(100), -- e.g., Đại học, Cao đẳng
        is_active BIT DEFAULT 1,
        created_at DATETIME2 DEFAULT GETDATE(),
        updated_at DATETIME2 DEFAULT GETDATE(),
        deleted_at DATETIME2,
        created_by UNIQUEIDENTIFIER,
        updated_by UNIQUEIDENTIFIER,
        deleted_by UNIQUEIDENTIFIER,
        CONSTRAINT FK_TP_Majors FOREIGN KEY (major_id) REFERENCES majors(id)
    );
    PRINT 'Created training_programs table';
END
GO

-- Sample Data
INSERT INTO departments (code, name, description) VALUES 
('CNTT', N'Công nghệ thông tin', N'Khoa Công nghệ thông tin'),
('KT', N'Kinh tế', N'Khoa Kinh tế');

INSERT INTO majors (code, name, department_id)
SELECT 'KTPM', N'Kỹ thuật phần mềm', id FROM departments WHERE code = 'CNTT';

INSERT INTO majors (code, name, department_id)
SELECT 'QTKD', N'Quản trị kinh doanh', id FROM departments WHERE code = 'KT';

INSERT INTO academic_years (code, name, start_year, end_year) VALUES
('K2021', N'Khóa 2021', 2021, 2025),
('K2022', N'Khóa 2022', 2022, 2026);

INSERT INTO training_programs (code, name, major_id, degree_level)
SELECT 'KTPM_DH', N'Chương trình Kỹ thuật phần mềm Đại học', id, N'Đại học' FROM majors WHERE code = 'KTPM';
GO
