function updateChart(){
    var moodId = $('#mood').val();
    $.get('/entry/metric/'+moodId, function (data){
        var myChart = echarts.init(document.getElementById('main'));
        var option = {
            xAxis: {
                type: 'category',
                data: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'],
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: 'white'
                    }
                }
            },
            yAxis: {
                type: 'value',
                axisLine: {
                    show: true,
                    lineStyle: {
                        color: 'white'
                    }
                }
            },
            series: [{
                data: data,
                type: 'bar'
            }],
            title:
             {
                text: 'Mood Frequency By Day    ',
                left: 'center',
                top: 10,
                textStyle: {
                    fontWeight: 'normal',
                    fontSize: 20,
                    color: 'white'
                }
            }
        };
        myChart.setOption(option);
    });
};
function initDialog(){
    $.get("/mood",function (data){
        var html_var = '';
        for(var i=0;i<data.length;i++){
            if(i==0){
                html_var+='<option value="'+data[i].moodId+'" selected="selected">'+data[i].description+'</option>';
            }
            else{
                html_var+='<option value="'+data[i].moodId+'">'+data[i].description+'</option>';
            }
        }
        $('#mood').html(html_var);
        updateChart();
    });
};

initDialog();

$('#mood').change(function (){
    updateChart();
});