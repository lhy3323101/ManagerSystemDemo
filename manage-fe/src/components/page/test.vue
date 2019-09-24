<template>
    <div>
        <el-button @click="testMethod()">
            测试数据
        </el-button>
        <div style="font-size: 20px">
            {{this.testText}}
        </div>
        <div>
            <el-date-picker
                    v-model="query.entryTime"
                    type="datetime"
                    :value-format="'yyyy-MM-dd HH:mm'"
                    :default-time="defaultTime"
                    :format="'yyyy-MM-dd HH:mm'">
            </el-date-picker>
        </div>
        <div>
            <date-picker v-model="testTime" :value-type="valueType" type="datetime" :disabled-days="this.disableDates" :placeholder="defaultText" :time-picker-options="timePickerOptions" :value-format="'YYYY-MM-DD HH:mm:ss'" format="YYYY-MM-DD HH:mm" confirm></date-picker>
        </div>
    </div>

</template>

<script>
    import {api} from '@/api/index.js'
    import DatePicker from 'vue2-datepicker'

    export default {
        name: "test.vue",
        components: { DatePicker },
        data :function(){
            return{
                defaultText:'请选择进厂时间',
                testText:'',
                defaultTime:'13:02:00',
                valueType:'timestamp',
                query:{
                    entryTime:'',
                },
                testTime: '',
                // custom range shortcuts
                shortcuts: [
                    {
                        text: 'Today',
                        onClick: () => {
                            this.time3 = [ new Date(), new Date() ]
                        }
                    }
                ],
                timePickerOptions:{
                    start: this.getStartTime(),
                    step: '00:30',
                    end: '18:30',
                },

            }
        },
        methods:{
            getStartTime(){
                return '07:30';
            },
            testMethod(){
                let param = {
                    testTime:this.testTime
                };
                console.log("vue testTime");
                console.log(this.testTime);
                console.log(param);
                api.test(param)
                    .then(data => {
                        console.log(data);
                    })
            },
            disableDates(time){
                return time.getTime() < Date.now() - 8.64e7;
            }
        }
    }
</script>

<style scoped>

</style>
