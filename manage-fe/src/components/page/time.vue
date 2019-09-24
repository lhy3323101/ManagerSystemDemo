<template>
    <div class="container">
        <div style="text-align: center">
            <h1>时间信息管理</h1>
        </div>
        <div style="margin-top: 20px">
            <el-row>
                <el-form :inline="true" :label-position="labelPosition" label-width="80px">
                    <el-form-item label="开始时间">
                        <el-time-select
                                v-model="searchInfo.startTimeStr"
                                :picker-options="{
                                start: '08:30',
                                end: '21:30',
                                step: '00:15',
                                }"
                                placeholder="请选择开始时间">
                        </el-time-select>
                    </el-form-item>
                    <el-form-item label="结束时间">
                        <el-time-select
                                v-model="searchInfo.endTimeStr"
                                :picker-options="{
                                start: '09:30',
                                end: '22:30',
                                step: '00:15',
                                }"
                                placeholder="请选择开始时间">
                        </el-time-select>
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
                    新增时间
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
                        prop="timeStr"
                        label="时间"
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
            <el-dialog title="新增/修改时间" center :visible.sync="dialogFormVisible" style="width:60%;margin-left: 23%">
                <el-form :model="newInfo" label-position="right" :rules="rules" ref="newInfo">
                    <el-form-item label="星期几：" :label-width="formLabelWidth" prop="weekName">
                        <el-select v-model="this.newInfo.weekName" placeholder="请选择">
                            <el-option
                                    v-for="item in weekList"
                                    :key="item.value"
                                    :label="item.label"
                                    :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="课程开始时间：" :label-width="formLabelWidth" prop="startTime">
                        <el-time-select
                                v-model="newInfo.startTimeStr"
                                :picker-options="{
                                start: '08:30',
                                end: '21:30',
                                step: '00:15',
                                }"
                                placeholder="请选择开始时间">
                        </el-time-select>
                    </el-form-item>
                    <el-form-item label="课程结束时间：" :label-width="formLabelWidth" prop="endTime">
                        <el-time-select
                                v-model="newInfo.endTimeStr"
                                :picker-options="{
                                start: '09:30',
                                end: '22:30',
                                step: '00:15',
                                }"
                                placeholder="请选择结束时间">
                        </el-time-select>
                    </el-form-item>
                    <el-form-item label="状态：" :label-width="formLabelWidth">
                        <el-radio v-model="newInfo.status" label="1" >启用</el-radio>
                        <el-radio v-model="newInfo.status" label="0" >禁用</el-radio>
                    </el-form-item>
                    <el-form-item label="备注：" :label-width="formLabelWidth" prop="remark">
                        <el-input v-model="newInfo.remark" clearable type="textarea" :rows="3" autocomplete="off" style="width: 50%" maxlength="11"></el-input>
                    </el-form-item>
                </el-form>
                <span slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="isModify ? update() : save()" style="margin-left: 10%" :loading="confirmLoading">确 定</el-button>
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
                    weekName: [
                        {required:true, trigger:'change', message: '请选择星期几'}
                    ],
                    startTimeStr: [
                        {required: true, trigger: 'change', message: '请选择开始时间'}
                    ],
                    endTimeStr: [
                        {required: true, trigger: 'change', message: '请选择结束时间'}
                    ],
                },
                searchInfo:{
                    startTimeStr:null,
                    endTimeStr:null
                },
                newInfo:{
                    status:'1',
                    startTimeStr:null,
                    endTimeStr:null,
                    remark:'',
                    id:'',
                    weekName:0,
                },
                weekList:[],
                pageSize:10,
                currentPage:1,
                total:0,
                labelPosition:'right',
                loading:false,
                tableData:[],
                formLabelWidth:'120px',
                dialogFormVisible:false,
                isModify:false,
                ban:false,
                confirmLoading:false,
            }
        },
        methods:{
            /** 搜索前准备工作*/
            search(){
                this.currentPage = 1;
                this.onSearch();
            },
            /** 搜索符合条件的时间信息*/
            onSearch(){
                this.loading =true;
                let message = this.$message;
                let param ={
                    startTimeStr:this.searchInfo.startTimeStr,
                    endTimeStr:this.searchInfo.endTimeStr,
                    pageStart:this.currentPage,
                    pageSize:this.pageSize
                };
                api.getTimeList(param)
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
            /** 修改时间信息准备 */
            modify(row){
                if (row){
                    this.newInfo.status = `${row.status}`;
                    this.newInfo.id = row.id;
                    this.newInfo.remark = row.remark;
                    this.newInfo.endTime = row.endTime;
                    this.newInfo.startTime = row.startTime;
                }
                this.isModify = true;
                this.ban = true;
                this.dialogFormVisible = true;
            },
            /** 修改时间信息*/
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
            /** 新建时间准备工作*/
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

            /** 保存时间信息*/
            save(){
                let message = this.$message;
                this.confirmLoading = true;
                this.$refs['newInfo'].validate(valid => {
                    if (valid){
                        let param = {
                            remark:this.newInfo.remark,
                            startTime:this.newInfo.startTime,
                            endTime:this.newInfo.endTime,
                            status:this.newInfo.status
                        };
                        api.saveNewTime(param)
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
                                this.confirmLoading = false;
                            })
                    }
                })
            },

            //获取星期列表
            getWeekList(){
                let message = this.$message;
                api.getWeekList()
                  .then(data => {
                      if (data.code === 1){
                          this.weekList = data.content;
                      }else {
                          message.error("获取星期列表失败，请联系管理员")
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
                this.getWeekList();
            }
        },
        mounted() {
            this.init();
        }
    }



</script>

<style scoped>

</style>
