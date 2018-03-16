use staffmanager;

insert into Department (DEPT_ID, DEPT_NAME, DEPT_NO, LOCATION)
values (10, 'ACCOUNTING', 'D10', 'NEW YORK');
  
insert into Department (DEPT_ID, DEPT_NAME, DEPT_NO, LOCATION)
values (20, 'RESEARCH', 'D20', 'DALLAS');
  
insert into Department (DEPT_ID, DEPT_NAME, DEPT_NO, LOCATION)
values (30, 'SALES', 'D30', 'CHICAGO');
  
insert into Department (DEPT_ID, DEPT_NAME, DEPT_NO, LOCATION)
values (40, 'OPERATIONS', 'D40', 'BOSTON');
  
-------------------------------------------------------------------------------------------------
  
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7839, 'KING', 'E7839', null, '1981-11-11', 'PRESIDENT', 5000, 10, null);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7566, 'JONES', 'E7566',null, '1981-11-15', 'MANAGER', 2975, 20, 7839);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7902, 'FORD', 'E7902',null, '1981-12-03', 'ANALYST', 3000, 20, 7566);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7369, 'SMITH', 'E7369',null, '1981-12-17', 'CLERK', 800, 20, 7902);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7698, 'BLAKE', 'E7698',null, '1981-05-01', 'MANAGER', 2850, 30, 7839);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7499, 'ALLEN', 'E7499',null, '1981-02-20', 'SALESMAN', 1600, 30, 7698);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7521, 'WARD', 'E7521',null, '1981-02-22', 'SALESMAN', 1250, 30, 7698);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7654, 'MARTIN', 'E7654',null, '1981-09-28', 'SALESMAN', 1250, 30, 7698);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7782, 'CLARK', 'E7782',null, '1981-06-09', 'MANAGER', 2450, 30, 7839);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7788, 'SCOTT', 'E7788',null, '1981-04-19', 'ANALYST', 3000, 20, 7566);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7844, 'TURNER', 'E7844',null, '1981-08-09', 'SALESMAN', 1500, 30, 7698);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7876, 'ADAMS', 'E7876',null, '1981-05-23', 'CLERK', 1100, 20, 7698);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7900, 'ADAMS', 'E7900',null, '1981-03-12', 'CLERK', 950, 30, 7698);
  
insert into Employee (EMP_ID, EMP_NAME, EMP_NO, IMAGE, HIRE_DATE, JOB, SALARY, DEPT_ID, MNG_ID)
values (7934, 'MILLER', 'E7934',null, '1982-01-23', 'CLERK', 1300, 10, 7698);
  
-------------------------------------------------------------------------------------------------
  
insert into Salary_Grade (GRADE, HIGH_SALARY, LOW_SALARY)
values (1, 9999, 3001);
