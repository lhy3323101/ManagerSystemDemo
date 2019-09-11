import request from '../utils/request';

let baseUrl = 'api';


export const api = {
    //Test
    test: params => {return request.post('/test',params)},
    /** 新增学员 */
    saveNewStudent: param =>{return request.post('/student/saveNewStudent',param)}
}
