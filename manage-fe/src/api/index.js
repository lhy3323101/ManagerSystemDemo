import request from '../utils/request';

let studentBaseUrl = '/student';

let courseBaseUrl = '/course';
let timeBaseUrl = '/time';

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
    getCourseList: param => {return request.post(`${courseBaseUrl}/getCourseList`,param)},
    /** 新增课程*/
    saveNewCourse: param => {return request.post(`${courseBaseUrl}/saveNewCourse`,param)},
    /** 修改课程 */
    updateCourse: param => {return request.post(`${courseBaseUrl}/updateCourse`,param)},

/** 时间API */
    /** 获取时间列表*/
    getTimeList: param => {return request.post(`${timeBaseUrl}/getTimeList`,param)},
    /** 新增时间 */
    saveNewTime: param => {return request.post(`${timeBaseUrl}/saveNewTime`,param)},
    /** 修改时间 */
    updateTime: param => {return request.post(`${timeBaseUrl}/updateTime`,param)},
    /** 获取星期列表 */
    getWeekList: () => {return request.get(`${timeBaseUrl}/getWeekList`)},

    //时间测试
    test: param => {return request.post(`${courseBaseUrl}/testTime`,param)}
}
