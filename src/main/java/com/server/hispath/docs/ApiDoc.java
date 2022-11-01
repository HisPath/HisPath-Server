package com.server.hispath.docs;

public class ApiDoc {


    /* Student 관련 API 명세 */


    /* Activity 관련 API 명세 */
    public static final String ACTIVITY_CREATE = "단일 활동 생성";
    public static final String ACTIVITY_READ = "단일 활동 조회";
    public static final String ACTIVITY_READ_ALL = "모든 활동 조회";
    public static final String ACTIVITY_UPDATE = "활동 수정";
    public static final String ACTIVITY_DELETE = "활동 삭제";
    public static final String MILEAGE_CREATE = "단일 마일리지 활동 등록";
    public static final String MILEAGES_CREATE = "단일 마일리지 다중 등록";
    public static final String MILEAGE_UPDATE = "마일리지 활동 수정";
    public static final String MILEAGE_DELETE = "마일리지 활동 삭제";
    public static final String MILEAGE_READ_SEMESTER = "승인된 마일리지 활동 학기별 조회";
    public static final String MILEAGE_READ_ALL = "모든 승인된 마일리지 활동 조회";
    public static final String MILEAGE_REGISTER_STUDENTS = "마일리지 활동 참가 학생 엑셀 등록";
    public static final String MILEAGE_REGISTER_STUDENT = "마일리지 활동 참가 학생 단일 등록";
    public static final String ACTIVITY_STUDENT_DELETE = "마일리지 활동 학생 참가 정보 삭제";
    public static final String MILEAGE_READ = "단일 마일리지 활동 조회";
    public static final String STUDENT_MILEAGE_READ = "학생별 마일리지 활동 조회";
    public static final String STUDENT_ACTIVITY_CREATE = "학생의 개인 활동 생성";
    public static final String STUDENT_ACTIVITY_UPDATE = "학생의 참여 활동 수정";
    public static final String STUDENT_ACTIVITY_READ_SEMESTER = "학생의 참여 활동 학기별 조회";
    public static final String SECTION_READ_ALL = "모든 섹션 조회";
    public static final String PARTICIPANT_DELETE = "학생 참여 데이터 삭제";
    /* Category 관련 API 명세 */
    public static final String CATEGORY_READ = "단일 카테고리 조회";
    public static final String CATEGORY_READ_ALL = "모든 카테고리 조회";
    public static final String CATEGORY_CREATE = "카테고리 생성";
    public static final String CATEGORY_UPDATE = "카테고리 수정";
    public static final String CATEGORY_DELETE = "카테고리 삭제";

    public static final String ACTIVITY_READ_SEMESTER = "활동 학기 조회";
    public static final String MAJOR_CREATE = "전공 생성";
    public static final String MAJOR_READ = "단일 전공 조회";
    public static final String MAJOR_READ_ALL = "모든 전공 조회";
    public static final String MAJOR_UPDATE = "전공 수정";
    public static final String MAJOR_DELETE = "전공 삭제";

    public static final String NOTICE_CREATE = "공지 생성";
    public static final String NOTICE_READ_ALL = "전체 공지 조회";
    public static final String NOTICE_READ = "단일 공지 조회";
    public static final String NOTICE_DELETE = "공지 삭제";
    public static final String NOTICE_UPDATE = "공지 수정";


    /* Student 관련 API 명세 */
    public static final String STUDENT_READ = "단일 학생 조회";
    public static final String STUDENT_READ_ALL = "모든 학생 조회";
    public static final String STUDENT_CREATE = "학생 추가";
    public static final String STUDENTS_CREATE = "학생 단일 추가";
    public static final String STUDENT_UPDATE = "학생 수정";
    public static final String STUDENT_DELETE = "학생 삭제";
    public static final String DASHBOARD = "메인페이지 대시보드 정보 조회";

    /* Manager 관련 API 명세 */
    public static final String MANAGER_READ = "단일 관리자 조회";
    public static final String MANAGER_READ_ALL = "모든 관리자 조회";
    public static final String MANAGER_CREATE = "관리자 생성";
    public static final String MANAGER_UPDATE = "관리자 수정";
    public static final String MANAGER_APPROVE = "관리자 승인";
    public static final String MANAGER_DELETE = "관리자 삭제";

    /* Department 관련 API 명세 */
    public static final String DEPARTMENT_READ = "단일 학부 조회";
    public static final String DEPARTMENT_READ_ALL = "모든 학부 조회";
    public static final String DEPARTMENT_CREATE = "학부 추가";
    public static final String DEPARTMENT_UPDATE = "학부 수정";
    public static final String DEPARTMENT_DELETE = "학부 삭제";


    /* Resume 관련 API 명세 */
    public static final String RESUME_READ = "학생의 단일 이력서 조회";
    public static final String RESUME_READ_ALL = "학생의 모든 이력서 조회";
    public static final String RESUME_CREATE = "단일 이력서 생성";
    public static final String RESUME_UPDATE = "이력서 수정";
    public static final String RESUME_DELETE = "이력서 삭제";
    public static final String RESUME_INFO = "학생의 모든 정보 및 활동정보 조회";

    /* Scholarship 관련 API 명세 */
    public static final String SCHOLARSHIP_CREATE = "장학금 신청 등록";
    public static final String SCHOLARSHIP_READ_ALL = "장학금 수혜자들 명단 조회";
    public static final String SCHOLARSHIP_ACTIVITIES = "장학금 수혜자 정보와 그 활동들 조회";
    public static final String APPROVE_SCHOLARSHIPS = "현학기 장학금 승인 명단 엑셀 업로드";
    public static final String SCHOLARSHIP_SEARCH_STUDENT = "장학금 수혜 학생 필터 검색";
}