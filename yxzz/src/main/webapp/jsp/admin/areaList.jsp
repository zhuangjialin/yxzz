<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<!-- Mirrored from www.zi-han.net/theme/hplus/table_jqgrid.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:20:02 GMT -->
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">


    <title>H+ 后台主题UI框架 - jqGird</title>
	<jsp:include page="../admin/common/common.jsp" />

    <style>
        /* Additional style to fix warning dialog position */

        #alertmod_table_list_2 {
            top: 900px !important;
        }
        
    html,
    body,
    #container {
        width: 100%;
        height: 100%;
        margin: 0px;
        font-size: 13px;
    }
    
    #pickerBox {
        position: absolute;
        z-index: 9999;
        right: 30px;
        width: 300px;
    }
    
    #pickerInput {
        width: 200px;
        padding: 5px 5px;
    }
    
    #poiInfo {
        background: #fff;
    }
    
    .amap_lib_placeSearch .poibox.highlight {
        background-color: #CAE1FF;
    }
    
    .amap_lib_placeSearch .poi-more {
        display: none!important;
    }
    </style>

</head>

<body class="gray-bg">





    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-content">
                        <div class="jqGrid_wrapper">
                        	<div style="padding:3px;">
                        		<button type="button" class="btn btn-w-m btn-primary" data-toggle="modal" data-target="#myModal">新增</button>
                        		<button type="button" class="btn btn-w-m btn-info" onclick="alert('编辑')">编辑</button>
                        		<button type="button" class="btn btn-w-m btn-danger" onclick="alert('删除')">删除</button>
                        	</div>
                            <table id="table_list_2"></table>
                            <div id="pager_list_2"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    

    
    
    
    
   <div class="modal inmodal fade" id="myModal" tabindex="-1" role="dialog"  aria-hidden="true">
          <div class="modal-dialog modal-lg">
              <div class="modal-content">

		<div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>所有表单元素 <small>包括自定义样式的复选和单选按钮</small></h5>
                    </div>
                    <div class="ibox-content">
                        <form method="get" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">名称</label>

                                <div class="col-sm-3">
                                    <input type="text" class="form-control">
                                </div>
                                
                                 <label class="col-sm-2 control-label">城市编码</label>

                                <div class="col-sm-3">
                                    <input type="text" class="form-control">
                                </div>
                            </div>
                            <div class="hr-line-dashed"></div>
                            
                            
               
                            
                            
                        </form>
                    </div>
                </div>
                 
                 

                  <div class="modal-footer">
                      <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                      <button type="button" class="btn btn-primary">保存</button>
                  </div>
              </div>
          </div>
      </div>



    <script>
    $(document).ready(function() {
    	  $.jgrid.defaults.styleUI = "Bootstrap";
    	  var mydata = [{
    		areaId: "1",
    		areaName: "2010-05-24",
    	    name: "test",
    	    note: "note",
    	    tax: "10.00",
    	    total: "2111.00"
    	  },
    	  {
    		  areaId: "28",
    		  areaName: "2007-10-03",
    	    name: "test2",
    	    note: "note2",
    	    amount: "300.00",
    	    tax: "20.00",
    	    total: "320.00"
    	  },
    	  {
    	    id: "29",
    	    invdate: "2007-09-01",
    	    name: "test3",
    	    note: "note3",
    	    amount: "400.00",
    	    tax: "30.00",
    	    total: "430.00"
    	  }];

    	  $("#table_list_2").jqGrid({
    	    data: mydata,
    	    datatype: "local",
    	    height: 450,
    	    autowidth: true,
    	    shrinkToFit: true,
    	    rowNum: 20,
    	    rowList: [10, 20, 30],
    	    colNames: ["区域ID", "区域名称", "城市ID", "所属地铁数量", "区域编码", "所属物业数量"],
    	    colModel: [{
    	      name: "areaId",
    	      index: "areaId",
    	      editable: true,
    	      width: 60,
    	      sorttype: "int",
    	      search: true
    	    },
    	    {
    	      name: "areaName",
    	      index: "areaName",
    	      editable: true,
    	      width: 90
    	    },
    	    {
    	      name: "areaCityId",
    	      index: "areaCityId",
    	      editable: true,
    	      width: 100
    	    },
    	    {
    	      name: "areaSubwayNum",
    	      index: "areaSubwayNum",
    	      editable: true,
    	      width: 80,
    	      align: "right",
    	      sorttype: "float",
    	      formatter: "number"
    	    },
    	    {
    	      name: "areaCode",
    	      index: "areaCode",
    	      editable: true,
    	      width: 80,
    	      align: "right",
    	      sorttype: "float"
    	    },
    	    {
    	      name: "areaPropertyNum",
    	      index: "areaPropertyNum",
    	      editable: true,
    	      width: 80,
    	      align: "right",
    	      sorttype: "float"
    	    }],
    	    pager: "#pager_list_2",
    	    viewrecords: true,
    	    caption: "区域列表",
    	    add: true,
    	    edit: true,
    	    addtext: "新增",
    	    edittext: "编辑",
    	    hidegrid: false
    	  });
    	  $("#table_list_2").setSelection(4, true);
    	  $("#table_list_2").jqGrid("navGrid", "#pager_list_2", {
    	    edit: true,
    	    add: true,
    	    del: true,
    	    search: true
    	  },
    	  {
    	    height: 200,
    	    reloadAfterSubmit: true
    	  });
    	  $(window).bind("resize",
    	  function() {
    	    var width = $(".jqGrid_wrapper").width();
    	    $("#table_list_2").setGridWidth(width)
    	  })
    	});    </script>

    
   
</body>


</html>
