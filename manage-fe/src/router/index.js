import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: resolve => require(['../components/common/Home.vue'], resolve),
            meta: { title: '自述文件' },
            children:[
                {
                    path: '/dashboard',
                    component: resolve => require(['../components/page/Dashboard.vue'], resolve),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/403',
                    component: resolve => require(['../components/page/403.vue'], resolve),
                    meta: { title: '403' }
                },
                {
                    path: '/student',
                    component: resolve => require(['../components/page/student.vue'], resolve),
                    meta: { title: '学生信息管理' }
                },
                {
                    path: '/course',
                    component: resolve => require(['../components/page/course.vue'], resolve),
                    meta: { title: '课程信息管理' }
                },
                {
                    path: '/test',
                    component: resolve => require(['../components/page/test.vue'], resolve),
                    meta: { title: '测试页' }
                }
            ]
        },
        {
            path: '/login',
            component: resolve => require(['../components/page/Login.vue'], resolve)
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
})
