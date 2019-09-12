import request from '../utils/request';

let studentBaseUrl = '/student';


export const api = {
    /** 新增学员 */
    saveNewStudent: param =>{return request.post(`${studentBaseUrl}/saveNewStudent`,param)},
    /** 获取学生列表*/
    getStudentList: param =>{return request.post(`${studentBaseUrl}/getStudentList`,param)},
    /** 修改学生资料*/
    updateStudent:param => {return request.post(`${studentBaseUrl}/updateStudent`,param)}
}
