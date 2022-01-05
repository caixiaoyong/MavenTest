package com.atguigu.WritableComparable;

import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * @author CZY
 * @date 2021/10/17 22:50
 * @description FlowBean
 */
public class FlowBean implements WritableComparable<FlowBean>{
    //1.定义私有的属性
    private Integer upFlow;
    private Integer downFlow;
    private Integer sumFlow;

    public FlowBean() {
    }

    public long getUpFlow() {
        return upFlow;
    }

    public void setUpFlow(Integer upFlow) {
        this.upFlow = upFlow;
    }

    public long getDownFlow() {
        return downFlow;
    }

    public void setDownFlow(Integer downFlow) {
        this.downFlow = downFlow;
    }

    public long getSumFlow() {
        return sumFlow;
    }

    public void setSumFlow(Integer sumFlow) {
        this.sumFlow = sumFlow;
    }

    /**
     *重载上传下载总和方法
     */
    public void setSumFlow(){
        this.sumFlow=this.upFlow+this.downFlow;
    }

    /**
     * 序列化方法
     * @param out
     * @throws IOException
     */
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeInt(upFlow);
        out.writeInt(downFlow);
        out.writeInt(sumFlow);
    }

    /**
     * 反序列化方法
     * @param in
     * @throws IOException
     */
    @Override
    public void readFields(DataInput in) throws IOException {
        upFlow=in.readInt();
        downFlow=in.readInt();
        sumFlow=in.readInt();
    }

    @Override
    public String toString() {
        return upFlow+"\t"+downFlow+"\t"+sumFlow;
    }

    /**
     * 先按照流量总数倒序排列，若总数相等再按上传顺序排序
     * @param o
     * @return
     */
    @Override
    public int compareTo(FlowBean o) {
        if (this.sumFlow<o.getSumFlow()){
            return 1;
        }else if (this.sumFlow>o.getSumFlow()){
            return -1;
        }else {
            if(this.upFlow<o.upFlow){
                return -1;
            }else if (this.upFlow>o.upFlow){
                return 1;
            }else {
                return 0;
            }
        }
    }
}
