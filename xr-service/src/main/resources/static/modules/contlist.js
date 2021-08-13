layui.define(["table","form"],function(t){var e=layui.$,i=layui.table,n=layui.form;i.render({elem:"#LAY-app-content-list",url:layui.setter.base+"json/content/list.js",cols:[[{type:"checkbox",fixed:"left"},{field:"id",width:100,title:"\u6587\u7ae0ID",sort:!0},{field:"label",title:"\u6587\u7ae0\u6807\u7b7e",minWidth:100},{field:"title",title:"\u6587\u7ae0\u6807\u9898"},{field:"author",title:"\u4f5c\u8005"},{field:"uploadtime",title:"\u4e0a\u4f20\u65f6\u95f4",sort:!0},{field:"status",title:"\u53d1\u5e03\u72b6\u6001",templet:"#buttonTpl",minWidth:80,align:"center"},{title:"\u64cd\u4f5c",minWidth:150,align:"center",fixed:"right",toolbar:"#table-content-list"}]],page:!0,limit:10,limits:[10,15,20,25,30],text:"\u5bf9\u4e0d\u8d77\uff0c\u52a0\u8f7d\u51fa\u73b0\u5f02\u5e38\uff01"}),i.on("tool(LAY-app-content-list)",function(t){var e=t.data;"del"===t.event?layer.confirm("\u786e\u5b9a\u5220\u9664\u6b64\u6587\u7ae0\uff1f",function(e){t.del(),layer.close(e)}):"edit"===t.event&&layer.open({type:2,title:"\u7f16\u8f91\u6587\u7ae0",content:"../../../views/app/content/listform.html?id="+e.id,maxmin:!0,area:["550px","550px"],btn:["\u786e\u5b9a","\u53d6\u6d88"],yes:function(e,i){var l=window["layui-layer-iframe"+e],a=i.find("iframe").contents().find("#layuiadmin-app-form-edit");l.layui.form.on("submit(layuiadmin-app-form-edit)",function(i){var l=i.field;t.update({label:l.label,title:l.title,author:l.author,status:l.status}),n.render(),layer.close(e)}),a.trigger("click")}})}),i.render({elem:"#LAY-app-content-tags",url:layui.setter.base+"json/content/tags.js",cols:[[{type:"numbers",fixed:"left"},{field:"id",width:100,title:"ID",sort:!0},{field:"tags",title:"\u5206\u7c7b\u540d",minWidth:100},{title:"\u64cd\u4f5c",width:150,align:"center",fixed:"right",toolbar:"#layuiadmin-app-cont-tagsbar"}]],text:"\u5bf9\u4e0d\u8d77\uff0c\u52a0\u8f7d\u51fa\u73b0\u5f02\u5e38\uff01"}),i.on("tool(LAY-app-content-tags)",function(t){var i=t.data;if("del"===t.event)layer.confirm("\u786e\u5b9a\u5220\u9664\u6b64\u5206\u7c7b\uff1f",function(e){t.del(),layer.close(e)});else if("edit"===t.event){e(t.tr);layer.open({type:2,title:"\u7f16\u8f91\u5206\u7c7b",content:"../../../views/app/content/tagsform.html?id="+i.id,area:["450px","200px"],btn:["\u786e\u5b9a","\u53d6\u6d88"],yes:function(e,i){var n=i.find("iframe").contents().find("#layuiadmin-app-form-tags"),l=n.find('input[name="tags"]').val();l.replace(/\s/g,"")&&(t.update({tags:l}),layer.close(e))},success:function(t,e){var n=(t.find("iframe").contents().find("#layuiadmin-app-form-tags").click(),window[t.find("iframe")[0].name]);n.layui.use("form",function(t){t.val("layuiadmin-form-tags",{tags:i.tags})})}})}}),i.render({elem:"#LAY-app-content-comm",url:layui.setter.base+"json/content/comment.js",cols:[[{type:"checkbox",fixed:"left"},{field:"id",width:100,title:"ID",sort:!0},{field:"reviewers",title:"\u8bc4\u8bba\u8005",minWidth:100},{field:"content",title:"\u8bc4\u8bba\u5185\u5bb9",minWidth:100},{field:"commtime",title:"\u8bc4\u8bba\u65f6\u95f4",minWidth:100,sort:!0},{title:"\u64cd\u4f5c",width:150,align:"center",fixed:"right",toolbar:"#table-content-com"}]],page:!0,limit:10,limits:[10,15,20,25,30],text:"\u5bf9\u4e0d\u8d77\uff0c\u52a0\u8f7d\u51fa\u73b0\u5f02\u5e38\uff01"}),i.on("tool(LAY-app-content-comm)",function(t){t.data;"del"===t.event?layer.confirm("\u786e\u5b9a\u5220\u9664\u6b64\u6761\u8bc4\u8bba\uff1f",function(e){t.del(),layer.close(e)}):"edit"===t.event&&layer.open({type:2,title:"\u7f16\u8f91\u8bc4\u8bba",content:"../../../views/app/content/contform.html",area:["450px","300px"],btn:["\u786e\u5b9a","\u53d6\u6d88"],yes:function(t,e){var n=window["layui-layer-iframe"+t],l="layuiadmin-app-comm-submit",a=e.find("iframe").contents().find("#"+l);n.layui.form.on("submit("+l+")",function(e){e.field;i.reload("LAY-app-content-comm"),layer.close(t)}),a.trigger("click")},success:function(t,e){}})}),t("contlist",{})});