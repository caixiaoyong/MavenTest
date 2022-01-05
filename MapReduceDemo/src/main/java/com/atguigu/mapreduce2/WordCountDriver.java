package com.atguigu.mapreduce2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 可以打包jar包到Linux操作系统使用
 * @author C Y
 * @date 2021/10/16 14:20
 * @description WordCountDriver
 */
public class WordCountDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //1.创建配置对象并获取job实例
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        //2.绑定当前driver或者jar
        job.setJarByClass(WordCountDriver.class);

        //3.绑定mapper和reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        //4.设置mapper的输出KV类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        //5.设置最终的输出KV类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //6.指定输入目录--args[0]、args[0]可以分别定义成常量,增强了通用性
        FileInputFormat.setInputPaths(job,new Path(args[0]));

        //7.指定输出目录
        FileOutputFormat.setOutputPath(job,new Path(args[1]));

        //8.提交运行
        boolean b = job.waitForCompletion(true);//为true监控并打印更多job
        System.exit(b?0:1);
    }
}
