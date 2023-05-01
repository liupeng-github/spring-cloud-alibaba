package cloud.liupeng.api.annotations;

import org.openjdk.jmh.annotations.*;
import java.util.concurrent.TimeUnit;

/**
 * JMH 性能测试基础
 *
 * @author liupeng
 */
@BenchmarkMode(Mode.AverageTime) // 测试完成时间
@State(Scope.Benchmark) // 指定一个对象的作用范围
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 为统计结果的时间单位，可用于类或者方法注解
@Warmup(iterations = 1, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 1 轮，每次 1s
@Measurement(iterations = 2, time = 3, timeUnit = TimeUnit.SECONDS) // 测试 2 轮，每次 3s
@Threads(10) // 开启 10 个并发线程
@Fork(1) // fork 1 个线程
public @interface Jmh {
    /**
     * @BenchmarkMode 注解
     * 用来配置 Mode 选项，可用于类或者方法上，这个注解的 value 是一个数组，可以把几种 Mode 集合在一起执行，如：@BenchmarkMode({Mode.SampleTime, Mode.AverageTime})，还可以设置为 Mode.All，即全部执行一遍。
     * 1、Throughput：整体吞吐量，每秒执行了多少次调用，单位为 ops/time
     * 2、AverageTime：用的平均时间，每次操作的平均时间，单位为 time/op
     * 3、SampleTime：随机取样，最后输出取样结果的分布
     * 4、SingleShotTime：只运行一次，往往同时把 Warmup 次数设为 0，用于测试冷启动时的性能
     * 5、All：上面的所有模式都执行一次
     */

    /**
     * @State 注解
     * 1、Scope.Benchmark：所有测试线程共享一个实例，测试有状态实例在多线程共享下的性能
     * 2、Scope.Group：同一个线程在同一个 group 里共享实例
     * 3、Scope.Thread：默认的 State，每个测试线程分配一个实例
     */

    /**
     * @OutputTimeUnit 注解
     * 为统计结果的时间单位，可用于类或者方法注解
     */

    /**
     * @Warmup 注解
     * 预热所需要配置的一些基本测试参数，可用于类或者方法上。一般前几次进行程序测试的时候都会比较慢，所以要让程序进行几轮预热，保证测试的准确性。
     * 1、iterations：预热的次数
     * 2、time：每次预热的时间
     * 3、timeUnit：时间的单位，默认秒
     * 4、batchSize：批处理大小，每次操作调用几次方法
     */

    /**
     * @Measurement 注解
     * 实际调用方法所需要配置的一些基本测试参数，可用于类或者方法上，参数和 @Warmup 相同。
     */

    /**
     * @Threads 注解
     * 每个进程中的测试线程，可用于类或者方法上。
     */

    /**
     * @Fork 注解
     * 进行 fork 的次数，可用于类或者方法上。如果 fork 数是 2 的话，则 JMH 会 fork 出两个进程来进行测试。
     */

    /**
     * @Param 注解
     * 指定某项参数的多种情况，特别适合用来测试一个函数在不同的参数输入的情况下的性能，只能作用在字段上，使用该注解必须定义 @State 注解。
     */
}
