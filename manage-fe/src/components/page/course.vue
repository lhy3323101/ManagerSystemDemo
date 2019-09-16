<template>
    <div class="container">
        <div style="text-align: center">
            <h1>课程信息管理</h1>
        </div>
        <div style="margin-top: 20px">
            <el-row>
                <el-form :inline="true" :label-position="labelPosition" label-width="80px">
                    <el-form-item label="课程名">
                        <el-input v-model="searchInfo.name"></el-input>
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
                    新增课程
                </el-button>
            </el-row>
            <el-row>
                <el-table
                    :data="tableData"
                    v-loading="loading"
                    height="450"
                    border
                    style="width: 100%;margin-top: 10px">
                    <el-table-column
                            label="序号"
                            type="index"
                            width="50">
                    </el-table-column>
                    <el-table-column
                        prop="name"
                        label="课程名"
                        width="180">
                    </el-table-column>
                    <el-table-column
                            prop="stuTime"
                            label="课时(小时)"
                            width="180">
                    </el-table-column>
                    <el-table-column
                            prop="price"
                            label="价格(元)"
                            width="180">
                    </el-table-column>
                    <el-table-column
                            prop="statusStr"
                            label="状态"
                            width="180">
                    </el-table-column>
                    <el-table-column
                        prop="remark"
                        label="备注">
                    </el-table-column>
                    <el-table-column
                        label="操作">
                        <template slot-scope="scope">
                            <el-button @click="modify(scope.row)">编辑</el-button>
                        </template>
                    </el-table-column>
                </el-table>
            </el-row>
        </div>
        <div>
            <el-dialog title="新增课程" center :visible.sync="dialogFormVisible" style="width:60%;margin-left: 23%">
                <el-form :model="newInfo" label-position="right" :rules="rules" ref="newInfo">
                    <el-form-item label="课程名：" :label-width="formLabelWidth" prop="name">
                        <el-input v-model="newInfo.name" clearable :disabled="this.ban" autocomplete="off" style="width: 50%" maxlength="125"></el-input>
                    </el-form-item>
                    <el-form-item label="价格：" :label-width="formLabelWidth" prop="price">
                        <el-input-number v-model="newInfo.price" :precision="2" :step="0.01" :min="0.00" :max="100000"></el-input-number>
                    </el-form-item>
                    <el-form-item label="课时：" :label-width="formLabelWidth" prop="stuTime">
                        <el-input-number v-model="newInfo.stuTime" :precision="1" :step="0.5" :min="1" :max="1000"></el-input-number>
                    </el-form-item>
                    <el-form-item label="状态：" :label-width="formLabelWidth">
                        <el-radio v-model="newInfo.status" label="0" >禁用</el-radio>
                        <el-radio v-model="newInfo.status" label="1" >启用</el-radio>
                    </el-form-item>
                    <el-form-item label="备注：" :label-width="formLabelWidth" prop="remark">
                        <el-input v-model="newInfo.remark" clearable type="textarea" :rows="3" autocomplete="off" style="width: 50%" maxlength="11"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="isModify ? update() : save()" style="margin-left: 10%">确 定</el-button>
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
                        {required: true, trigger: 'blur', message: "请输入课程名"}
                    ],
                },
                searchInfo:{
                    name:'',
                },
                newInfo:{
                    name:'',
                    status:'1',
                    remark:'',
                    price:1.00,
                    stuTime:0,
                    id:'',
                },
                pageSize:10,
                currentPage:1,
                total:0,
                labelPosition:'right',
                loading:false,
                tableData:[],
                formLabelWidth:'80px',
                dialogFormVisible:false,
                isModify:false,
                ban:false,
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
                    pageStart:this.currentPage,
                    pageSize:this.pageSize
                };
                api.getCourseList(param)
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
            /** 修改学员信息准备 */
            modify(row){
                if (row){
                    this.newInfo.status = `${row.status}`;
                    this.newInfo.name = row.name;
                    this.newInfo.id = row.id;
                    this.newInfo.stuTime = row.stuTime;
                    this.newInfo.remark = row.remark;
                    this.newInfo.price = row.price;
                }
                this.isModify = true;
                this.ban = true;
                this.dialogFormVisible = true;
            },
            /** 修改学员信息*/
            update(){
                let message = this.$message;
                this.$refs['newInfo'].validate(valid => {
                    if (valid){
                        api.updateCourse(this.newInfo)
                            .then(data => {
                                if (data.code === 1){
                                    message.success("修改成功");
                                    this.onSearch();
                                }else {
                                    message.error("修改课程信息失败，请联系管理员")
                                }
                            })
                            .catch(() => {
                                message.error("修改课程异常，请联系管理员")
                            })
                            .finally(() =>{
                                this.dialogFormVisible = false;
                            })
                    }
                })
            },
            /** 新建学员准备工作*/
            add(){
                if (this.$refs["newInfo"]!==undefined) {
                    this.$refs["newInfo"].resetFields();
                }
                this.newInfo = {
                    name:'',
                    status:'1',
                    price:0.00,
                    stuTime:0,
                    remark:'',
                };
                this.ban = false;
                this.isModify = false;
                this.dialogFormVisible = true;
                },

            /** 保存学员信息*/
            save(){
                let message = this.$message;
                this.$refs['newInfo'].validate(valid => {
                    if (valid){
                        let param = {
                            name:this.newInfo.name,
                            price:this.newInfo.price,
                            stuTime:this.newInfo.stuTime,
                            remark:this.newInfo.remark,
                        };
                        api.saveNewCourse(param)
                            .then(data =>{
                                if (data.code === 1){
                                    message.success("添加成功");
                                    this.onSearch();
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
            /** 校验手机号*/
            validateMobile(value) {
                let reg = /^1[3456789]\d{9}$/;
                return reg.test(value);
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
