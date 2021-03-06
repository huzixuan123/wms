<%--
  Created by IntelliJ IDEA.
  User: apple
  Date: 2017/10/23
  Time: 15:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>ECharts</title>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="height:600px;width: 800px"></div>
<!-- ECharts单文件引入 -->
<script src="/js/plugins/echarts/echarts-all.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main'));

    option = {
        title : {
            text: "销售总额",
            subtext: "销售人员",
            x:'center'
        },
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient : 'vertical',
            x : 'left',
            data:["admin","李四明"]
        },
        toolbox: {
            show : true,
            feature : {
                mark : {show: true},
                dataView : {show: true, readOnly: false},
                magicType : {
                    show: true,
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%',
                            funnelAlign: 'left',
                            max: 1548
                        }
                    }
                },
                restore : {show: true},
                saveAsImage : {show: true}
            }
        },
        calculable : true,
        series : [
            {
                name:'销售总额',
                type:'pie',
                radius : '55%',
                center: ['50%', '60%'],
                data:[
                    {value:'1667400',name:"admin"},
                    {value:'288000',name:"李四明"}

                ]
            }
        ]
    };

    // 为echarts对象加载数据
    myChart.setOption(option);
</script>
</body>