<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="path" value="${pageContext.request.contextPath }"></c:set>
<base href="${path}/">
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge，chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, user-scalable=0">
<meta name="keywords" content="111">
<meta name="description" content="111">
<title>我的收货地址</title>
<link rel="stylesheet" href="static/address/css/style.css">
<!--[if lt IE 9]>
    <script src="http://cdn.static.runoob.com/libs/html5shiv/3.7/html5shiv.min.js"></script>
    <![endif]-->
</head>
<body>

	<!--the-address-->
	<div class="the-address">
		<div class="adr-tent" id="vue-address">
			<table class="adr-table">
				<thead class="table-thead">
					<tr>
						<th rowspan="1" class="thh"><span class="tn">收货人</span></th>
						<th rowspan="1" class="thh"><span class="tn">所在地区</span></th>
						<th rowspan="1" class="thh"><span class="tn">详细地址</span></th>
						<th rowspan="1" class="thh"><span class="tn">邮编</span></th>
						<th rowspan="1" class="thh"><span class="tn">电话/手机</span></th>
						<th rowspan="1" class="thh"><span class="tn">操作</span></th>
						<th rowspan="1" class="thh"><span class="tn">设置</span></th>
					</tr>
				</thead>
				<tbody class="table-tbody">
					<tr class="item" v-for="(v,i) in addressList">
						<td class="tdd"><span class="ti"> <em class="tt">{{v.name}}</em>
						</span></td>
						<td class="tdd"><span class="ti"> <em class="tt">{{v.address}}</em>
						</span></td>
						<td class="tdd"><span class="ti"> <em class="tt">{{v.detailAddress}}</em>
						</span></td>
						<td class="tdd"><span class="ti"> <em class="tt">{{v.zipCode}}</em>
						</span></td>
						<td class="tdd"><span class="ti"> <em class="tt">{{v.phone}}</em>
						</span></td>
						<td class="tdd"><span class="ti">
								<div class="handle">
									<a href="javascript:;" class="a-chg">修改</a> <i class="line">|</i>
									<em class="i-del">删除</em>
								</div>
						</span></td>
						<td class="tdd"><span class="ti"> <em
								class="set-def cur" v-if="v.isDefault">默认地址</em> <em
								class="set-def" v-else @click="setDefault(i)">设为默认</em>
						</span></td>
					</tr>

				</tbody>
			</table>
		</div>
	</div>

	<script src="static/address/plugins/vue/vue.js"></script>
	<script src="static/address/plugins/vue/axios.js"></script>

	<script>
    //地址管理
    const vueAddress = new Vue({
        el: '#vue-address',
        data(){
            return{
                addressList: [], //地址列表
            }
        },
        created(){
            this.getAddressJson();
        },
        methods:{
            //获取地址列表数据
            getAddressJson(){
                let url = 'static/address/json/addressTest.json';
                axios.get(url)
                    .then(response =>{
                        this.addressList = response.data.list;
                    })
                    .catch(error => {
                        console.log(error)
                    })
            },

            //设置默认地址
            setDefault(i){
                const addressList = this.addressList;
                addressList.forEach((item, index) => {
                    item.isDefault = index == i;
                });
                addressList.splice(0, 0, ...addressList.splice(i, 1));
            }

        }
    });
</script>
</body>
</html>