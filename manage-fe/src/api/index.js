import request from '../utils/request';

let studentBaseUrl = '/student';


export const api = {
/** 学员api */
    /** 新增学员 */
    saveNewStudent: param =>{return request.post(`${studentBaseUrl}/saveNewStudent`,param)},
    /** 获取学生列表*/
    getStudentList: param =>{return request.post(`${studentBaseUrl}/getStudentList`,param)},
    /** 修改学生资料*/
    updateStudent:param => {return request.post(`${studentBaseUrl}/updateStudent`,param)},

/** 课程API */
    /** 获取课程列表*/
    getCourseList: param => {return request.post(`${studentBaseUrl}/getCourseList`,param)},
    /** 新增课程*/
    saveNewCourse: param => {return request.post(`${studentBaseUrl}/saveNewCourse`,param)},
    /** 修改课程 */
    updateCourse: param => {return request.post(`${studentBaseUrl}/updateCourse`,param)},
}
