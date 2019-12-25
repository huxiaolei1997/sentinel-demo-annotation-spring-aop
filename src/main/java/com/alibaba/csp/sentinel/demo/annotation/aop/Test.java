package com.alibaba.csp.sentinel.demo.annotation.aop;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        initFlowRules();

        while (true) {
            Entry entry = null;
            try {
                entry = SphU.entry("HelloWorld");
                System.out.println("hello world");
            } catch (BlockException e) {
                System.out.println("blocked");
            } finally {
                if (entry != null) {
                    entry.exit();
                }
            }
        }
    }

    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>();
        FlowRule rule = new FlowRule();
        // 设置资源，通过规则来指定允许该资源通过的请求次数，例如下面的代码定义了资源 HelloWorld 每秒最多只能通过 20 个请求。
        // Demo 运行之后，我们可以在日志 ~/logs/csp/${appName}-metrics.log.xxx 里看到下面的输出
        rule.setResource("HelloWorld");
        rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        rule.setCount(20);
        // set limit QPS to 20
        rules.add(rule);
        FlowRuleManager.loadRules(rules);
    }
}
