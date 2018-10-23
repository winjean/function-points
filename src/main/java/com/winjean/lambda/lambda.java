package com.winjean.lambda;

import java.util.Arrays;
import java.util.List;

/**
 * 方法描述：
 * 创建人：winjean
 * 参数：
 * 创建时间：2018/7/28$ 13:39$
 * 修改人：winjean
 * 修改时间：2018/7/28$ 13:39$
 * 修改备注：
 * 版权所有权：江苏艾盾网络科技有限公司
 *
 * @version V1.0
 */
public class lambda {
    public static void main(String[] args) {
        learnLambda();
    }

    private static void learnLambda(){
        List<Integer> lists = Arrays.asList(8,2,3,4,1,6,7,1,5,9);

        System.out.print("查看list中每一个元素:");
        lists.forEach(m -> System.out.print(m +" "));
        System.out.println();

        System.out.print("获取list中最小值:");
        lists.stream().min(Integer::compareTo).ifPresent(System.out :: print);
        System.out.println();

        System.out.print("获取list中最大值:");
        lists.stream().max(Integer::compareTo).ifPresent(System.out ::println);

        System.out.print("将list进行排序:");
        lists.stream().sorted().forEach(m -> System.out.print(m + " "));
        System.out.println();

        System.out.print("将list进行过滤:");
        lists.stream().filter(k -> k>3).sorted().forEach(m -> System.out.print(m + " "));
        System.out.println();

        System.out.print("list中数量:");
        System.out.println(lists.stream().count());

        System.out.print("对list中每一个元素进行操作:");
        lists.stream().map(m -> m + 2).forEach(m -> System.out.print(m + " "));
    }
}
