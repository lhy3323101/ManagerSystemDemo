<template>
    <div class="container">
        <div style="text-align: center">
            <h1>学生信息管理</h1>
        </div>
        <div style="margin-top: 20px">
            <el-row>
                <el-form :inline="true" :label-position="labelPosition" label-width="80px">
                    <el-form-item label="学生姓名">
                        <el-input v-model="searchInfo.name"></el-input>
                    </el-form-item>
                    <el-form-item label="联系电话">
                        <el-input v-model="searchInfo.mobile"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="search">
                        搜索
                        </el-button>
                    </el-form-item>
                </el-form>
            </el-row>
            <el-row>
                <el-button type="primary" @click="add">
                    新增学员
                </el-button>
            </el-row>
            <el-row>
                <el-table
                    :data="tableData"
                    v-loading="loading"
                    height="450"
                    style="width: 100%;margin-top: 10px">
                    <el-table-column
                            prop="name"
                            label="姓名"
                            width="180">
                    </el-table-column>
                    <el-table-column
                            prop="age"
                            label="年龄"
                            width="180">
                    </el-table-column>
                    <el-table-column
                            prop="className"
                            label="就读班级">
                    </el-table-column>
                    <el-table-column
                            prop="studyTime"
                            label="上课时间">
                    </el-table-column>
                    <el-table-column
                            prop="parentName"
                            label="家长姓名">
                    </el-table-column>
                    <el-table-column
                            prop="parentMobile"
                            label="家长电话">
                    </el-table-column>
                    <el-table-column
                            prop="remark"
                            label="备注">
                    </el-table-column>
                </el-table>
            </el-row>
        </div>
        <div>
            <el-dialog title="新增学员" center :visible.sync="dialogFormVisible" style="width:60%;margin-left: 23%">
                <el-form :model="newInfo" label-position="right" :rules="rules" ref="newInfo">
                    <el-form-item label="学员姓名" :label-width="formLabelWidth" prop="name">
                        <el-input v-model="newInfo.name" autocomplete="off" style="width: 50%" maxlength="125"></el-input>
                    </el-form-item>
                    <el-form-item label="性别" :label-width="formLabelWidth">
                        <el-radio v-model="newInfo.sex" label="0">男</el-radio>
                        <el-radio v-model="newInfo.sex" label="1">女</el-radio>
                    </el-form-item>
                    <el-form-item label="学员年龄" :label-width="formLabelWidth" prop="age">
                        <el-input v-model="newInfo.age" autocomplete="off" style="width: 50%" maxlength="3"></el-input>
                    </el-form-item>
                    <el-form-item label="学员电话" :label-width="formLabelWidth" prop="mobile">
                        <el-input v-model="newInfo.mobile" autocomplete="off" style="width: 50%" maxlength="11"></el-input>
                    </el-form-item>
                    <el-form-item label="家长姓名" :label-width="formLabelWidth" prop="parentName">
                        <el-input v-model="newInfo.parentName" autocomplete="off" style="width: 50%" maxlength="125"></el-input>
                    </el-form-item>
                    <el-form-item label="家长电话" :label-width="formLabelWidth" prop="parentMobile">
                        <el-input v-model="newInfo.parentMobile" autocomplete="off" style="width: 50%" maxlength="11"></el-input>
                    </el-form-item>
                    <el-form-item label="备注" :label-width="formLabelWidth" prop="remark">
                        <el-input v-model="newInfo.remark" type="textarea" :rows="3" autocomplete="off" style="width: 50%" maxlength="11"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="save" style="margin-left: 10%">确 定</el-button>
                </span>
            </el-dialog>
        </div>
        <div class="block" style="text-align: center;margin-top: 30px">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :page-sizes="[10, 50, 100]"
                    :page-size="pageSize"
                    :current-page="currentPage"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    import {api} from '@/api/index.js'

    export default {
        name: "student",
        data(){
            return{
                rules:{
                    name: [
                        {required: true, trigger: 'blur', message: "请输入学员姓名"}
                    ],
                    age: [
                        {required: true, trigger: 'blur', message: "请输入学员年龄"}
                    ],
                    parentName: [
                        {required: true, trigger: 'blur', message: "请输入家长姓名"}
                    ],
                    mobile: [
                        {required: true, trigger: 'blur', message: "请输入学员联系电话"}
                    ],
                    parentMobile: [
                        {required: true, trigger: 'blur', message: "请输入家长联系电话"}
                    ],
                },
                searchInfo:{
                    name:'',
                    mobile:'',
                },
                newInfo:{
                    name:'',
                    sex:'0',
                    mobile:'',
                    parentName:'',
                    parentMobile:'',
                    age:null,
                    remark:'',
                },
                pageSize:10,
                currentPage:1,
                total:0,
                labelPosition:'right',
                loading:false,
                tableData:[],
                dialogFormVisible:false,
                formLabelWidth:'80px',
            }
        },
        methods:{
            /** 搜索前准备工作*/
            search(){
                this.currentPage = 1;
                this.onSearch();
            },
            /** 搜索符合条件的学员信息*/
            onSearch(){
                this.loading =true;
                let message = this.$message;
                let param ={
                    name:this.searchInfo.name,
                    mobile:this.searchInfo.mobile,
                    pageStart:this.currentPage,
                    pageSize:this.pageSize
                };
                api.getStudentList(param)
                    .then(data => {
                        if (data.code === 1){
                            this.tableData = data.content.list;
                            this.total = data.content.total;
                        }else {
                            message.error("获取数据失败，请联系管理员")
                        }
                    })
                    .catch(() =>{
                        message.error("获取数据异常，请联系管理员")
                    })
                    .finally(() => {
                        this.loading = false;
                    })
            },
            /** 新建学员准备工作*/
            add(){
                if (this.$refs["newInfo"]!==undefined) {
                    this.$refs["newInfo"].resetFields();
                }
                this.dialogFormVisible = true;

                },
            /** 保存学员信息*/
            save(){
                let message = this.$message;
                this.$refs['newInfo'].validate(valid => {
                    if (valid){
                        let param = {
                            name:this.newInfo.name,
                            sex:this.newInfo.sex,
                            mobile:this.newInfo.mobile,
                            parentName:this.newInfo.parentName,
                            parentMobile:this.newInfo.parentMobile,
                            age:this.newInfo.age,
                            remark:this.newInfo.remark,
                        };
                        console.log(param);
                        api.saveNewStudent(param)
                            .then(data =>{
                                if (data.code === 1){
                                    message.success("添加成功");
                                }else {
                                    message.error("添加失败，请联系管理员");
                                }
                            })
                            .catch(err => {
                                message.error("添加异常，请稍后再次");
                            })
                            .finally(() => {
                                this.dialogFormVisible = false;
                            })
                    }
                })
            },
            handleSizeChange(val) {
                this.pageSize = val;
                this.onSearch();
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.onSearch();
            },
            init(){
                this.onSearch();
            }
        },
        mounted() {
            this.init();
        }
    }



</script>

<style scoped>

</style>
