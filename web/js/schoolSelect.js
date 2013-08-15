/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

var schools = [
'上海交通大学','同济大学','复旦大学','华东理工大学','东华大学','华东师范大学','上海外国语大学','上海财经大学','上海海关学院','上海大学','上海理工大学',
'上海海事大学','上海工程技术大学','上海海洋大学','上海中医药大学','上海师范大学','华东政法大学','上海政法学院','上海建桥学院','上海第二工业大学','上海应用技术学院','上海电力学院','上海电机学院','上海对外贸易学院','上海金融学院','上海立信会计学院',
'上海体育学院','上海音乐学院','上海戏剧学院','上海商学院','上海杉达学院'
];
$().ready(function() {
    $("#schoolSelect").autocomplete(schools,{
		minChars: 0,
		max: 5,
		autoFill: true,
		matchContains: true,
		scrollHeight: 220,
		formatItem: function(data, i, total) {
			return "<I>"+data[0]+"</I>";
		},
		formatMatch: function(data, i, total) {
			return data[0];
		},
		formatResult: function(data) {
			return data[0];
		}
	});
});
