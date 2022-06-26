<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>좌석 선택</title>
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <%--    <link href="${pageContext.request.contextPath}/resources/css/seat.css" rel="stylesheet">--%>
    <!-- iamport.payment.js -->
    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.8.js"></script>
    <% request.setCharacterEncoding("UTF-8"); %>
    <style>
        *{
            margin: auto;
            padding: 0%;
        }
        .background{
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: #E2E2E2;
            z-index: -2;
        }
        .wrapper{
            width: 80%;
            height:950px;
            margin-top: 0.5%;
            /*border: 3px solid #666666;*/
            border-radius: 20px;
            background: white;
            box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 5px 0px, rgba(0, 0, 0, 0.1) 0px 0px 1px 0px;
        }

        .wrapper-left{
            width:48%;
            height:900px;
            border-radius: 20px;
            float: left;
            margin-top: 1.5%;
            margin-left: 1%;
            border-radius: 10px;
            box-shadow: rgba(0, 0, 0, 0.12) 0px 1px 3px, rgba(0, 0, 0, 0.24) 0px 1px 2px;
        }
        .wrapper-right{
            width:48%;
            height:650px;
            /*테두리 지우기*/
            float: right;
            margin-top: 10%;
            margin-right: 1%;
            border-radius: 10px;
            box-shadow: rgba(0, 0, 0, 0.12) 0px 1px 3px, rgba(0, 0, 0, 0.24) 0px 1px 2px;
        }
        .box-info{
            width: 100%;
            height: 30%;
            margin:auto;
        }
        .box-reserve-info{
            width: 60%;
            height: 100%;
        }

        .table-info{
            width: 100%;
            height: 50%;
            text-align: center;
            /*border: 1px solid pink;*/
            border-collapse: collapse;
            margin-top: 16%;
        }
        th, td{
            font-weight: bolder;
            border-bottom: 1px solid black;
            margin-bottom: 1%;
        }
        .box-payinfo{
            width: 65%;
            height: 59.5%;
            float: left;
            margin-top: -5%;
        }
        .box-pay-num{
            width: 98%;
            height: 38%;
        }
        .box-pay-num>div{
            margin-left: 2%;
            text-align: center;
            margin-top: 2%;
        }
        .box-teen-num{
            width: 30%;
            height: 100%;
            border: 2px solid #626262;
            border-radius: 20px;
            float: left;
        }
        .box-teen-num >h1{
            padding-top: 10%;
        }
        .box-teen-num >h2{
            padding-top: 16%;
        }
        .box-teen-num >input{
            margin-top: 12%;
            text-align: center;
            width: 80%;
            font-size: 40px;
            border: none;
            background-color: white;
        }
        .box-adult-num{
            width: 30%;
            height: 100%;
            border: 2px solid #626262;
            border-radius: 20px;
            float: left;
            display: inline-block;
        }
        .box-adult-num >h1{
            padding-top: 10%;
        }
        .box-adult-num >h2{
            padding-top: 16%;
        }
        .box-adult-num>input{
            margin-top: 12%;
            text-align: center;
            width: 80%;
            font-size: 40px;
            border: none;
            background-color: white;
        }
        .box-total-num{
            width: 30%;
            height: 100%;
            border: 2px solid #626262;
            border-radius: 20px;
            float: left;
            display: inline-block;
        }
        .box-total-num >h1{
            padding-top: 10%;
        }
        .box-total-num>h2{
            padding-top: 16%;
        }
        .box-total-num>input{
            margin-top: 12%;
            text-align: center;
            width: 80%;
            font-size: 40px;
            border: none;
            background-color: white;
        }
        .box-pay-select{
            width: 98%;
            height: 61%;

        }
        #teenPrice{
            text-align: center;
            width: 95%;
            height: 60%;
            overflow: hidden;
            font-size: 20px;
            margin-top: 10%;
        }
        #adultPrice{
            text-align: center;
            width: 95%;
            height: 60%;
            overflow: hidden;
            margin-top: 5%;
            font-size: 20px;
            margin-top: 10%;
        }
        .box-pay-teen{
            width: 45%;
            height: 90%;
            border: 2px solid #626262;
            float: left;
            margin-left: 4%;
            margin-top: 2%;
            text-align: center;
        }
        .box-pay-teen>input{
            background: white;
            width: 100%;
            color: black;
            font-weight: bolder;
            border: none;
            height: 10%;
            font-size: 20px;
            margin-top: 12%;
            text-align: center;
        }
        .box-pay-adult{
            width: 45%;
            height: 90%;
            border: 2px solid #626262;
            float: right;
            margin-right: 3%;
            margin-top: 2%;
            text-align: center;
        }
        .box-pay-adult>input{
            background: white;
            color: black;
            font-weight: bolder;
            width: 100%;
            border: none;
            height: 10%;
            font-size: 20px;
            margin-top: 12%;
            text-align: center;
        }

        .box-pay{
            width: 31.3%;
            height: 59.5%;
            float: right;
            margin-right: 2%;
            margin-top: -5%;
        }
        .box-pay-info{
            margin-top: 5%;
            height: 60%;
            border: 2px solid #626262;
            text-align: center;
        }
        .box-pay-info>input{
            text-align: center;
            margin-top: 5%;
            width: 80%;
            height: 10%;
        }
        .box-pay-info>table{
            margin-top: 20%;
            margin-bottom: 10%;
        }
        .box-pay-info>h2{
            margin-top: 10%;
        }
        #tPrice{
            text-align: right;
            border: none;
            background-color: white;
        }
        #aPrice{
            text-align: right;
            border: none;
            background-color: white;
        }
        #totalPrice{
            width: 80%;
            height: 15%;
            border: none;
            background-color: white;
            font-size: 20px;
        }
        .btn-pay{
            margin-top: 20%;
            width: 100%;
            height: 20%;
            border: none;
            border-radius: 20px;
            z-index: 5;
            color: whitesmoke;
            background: #ff2c31;
            font-weight: bold;
            font-size: 20px;
            box-shadow: rgba(0, 0, 0, 0.17) 0px -23px 25px 0px inset, rgba(0, 0, 0, 0.15) 0px -36px 30px 0px inset, rgba(0, 0, 0, 0.1) 0px -79px 40px 0px inset, rgba(0, 0, 0, 0.06) 0px 2px 1px, rgba(0, 0, 0, 0.09) 0px 4px 2px, rgba(0, 0, 0, 0.09) 0px 8px 4px, rgba(0, 0, 0, 0.09) 0px 16px 8px, rgba(0, 0, 0, 0.09) 0px 32px 16px;

        }
        .mainScreen{
            width: 80%;
            height: 10%;
            border: 2px solid black;
            margin: auto;
            margin-top: 7%;
            transform: perspective(100px) rotateX(-5deg);
            text-align: center;
            line-height: 350%;
            margin-bottom: 7%;
        }
        .hseat{
            zoom : 3;
            margin-left: 3.35%;
            accent-color: red;
        }
    </style>
</head>
<body>
<% response.setContentType("text/html;charset=UTF-8"); %>
<div class="background"></div>
<div class="wrapper">
    <div class="wrapper-middle">
        <div class="wrapper-left">
            <div class="box-info">
                <div class="box-pic"></div>
                <div class="box-reserve-info">
                    <input type="hidden" id="mRegion" value="${ReserveDetail.theaterName}">
                    <input type="hidden" id="mhall" value="${ReserveDetail.hall}">
                    <input type="hidden" id="mdate" value="${ReserveDetail.date}">
                    <input type="hidden" id="mtime" value="${ReserveDetail.time}">
                    <input type="hidden" id="mMcode" value="${ReserveDetail.mcode}">
                    <input type="hidden" id="mName" value="${ReserveDetail.movieName}">
                    <table class="table-info">
                        <tr>
                            <td>제목</td>
                            <td colspan="2">${ReserveDetail.movieName}</td>
                        </tr>
                        <tr>
                            <td>일자</td>
                            <td colspan="2">${ReserveDetail.date}</td>
                        </tr>
                        <tr>
                            <td>시간</td>
                            <td>${ReserveDetail.time}</td>
                            <td>${rst}</td>
                        </tr>
                        <tr>
                            <td>영화관</td>
                            <td colspan="2">${ReserveDetail.regionName} | ${ReserveDetail.theaterName}</td>
                        </tr>
                        <tr>
                            <td>상영관</td>
                            <td colspan="2">${ReserveDetail.hall}</td>
                        </tr>
                    </table>
                </div>
            </div>

            <%
                session.setAttribute("uid", "user1");
                String id = (String) session.getAttribute("uid");
            %>
            <script>
                /*==================== 좌석 선택 ====================*/
                $(document).ready(function() {
                    let count = 0;
                    let numarr = new Array();

                    $('.hseat').on('click',function () {
                        let svalue = $(this).val();//선택한 좌석 value값 가져옴 (a1~g10)
                        let num = $('#totalNum').val(); //총 인원
                        let val = this.checked;

                        if(val == true){
                            count+=1;
                        }else if(val == false){
                            count-=1;
                        }
                        numarr.push(svalue); //선택 좌석 배열에 넣음
                        if(!num){
                            alert('인원을 선택해주세요');
                            this.checked = false;
                            --count;
                            numarr.pop();
                        }
                        //초기화 1
                        if(count > num && num){
                            alert('더 이상 선택할 수 없습니다.');
                            this.checked = false;
                            --count;
                            numarr.pop();
                        } });
                    //초기화 2
                    $('#teenPrice, #adultPrice').on('change', function(){ count = 0; });
                    $('.btn-pay').on("click",function () {
                        let seatarr = new Array();
                        let seatInfo = '';
                        for (let i = 0; i < numarr.length; i++) {
                            seatarr[i] = numarr[i];
                            seatInfo += seatarr[i];
                            seatInfo += ' '; //a1 a2 a3
                        }
                        //상영관 코드
                        let hall = $('#mhall').val();
                        //uid
                        let uid = $('#userId').val();
                        //좌석 => seatInfo
                        //mcode
                        let mCode = $('#mMcode').val();
                        //날짜
                        let date = $('#mdate').val(); //날짜
                        //가격
                        let tNUm = $('#teenNum').val();
                        let tPrice = $('#teenPrice').val();

                        let aNUm = $('#adultNum').val();
                        let aPrice = $('#adultPrice').val();

                        let pcode1 = Number(tPrice)/Number(tNUm);
                        let pcode2 = Number(aPrice)/Number(aNUm);

                        //시간
                        let time = $('#mtime').val(); //영화 시작 시간

                        /*----------------------------------*/
                        let movName = $('#mName').val();
                        let region = $('#mRegion').val();

                        // let place = region + " " + hall;
                        let place = hall;
                        let num = $('#totalNum').val();
                        if(num > 0 && seatarr[0] != null){
                            let totalSum = $('#htotalprice').val();
                            let sum = totalSum + " " + num;

                            //상영관 코드, 사용자 아이디, 좌석 번호 코드, 영화 코드, 예매 날짜
                            var IMP = window.IMP;
                            IMP.init('imp55234985');
                            IMP.request_pay({
                                pg : 'inicis',
                                pay_method : 'card',
                                merchant_uid : 'merchant_' + new Date().getTime(),
                                name : movName,  //MovieName
                                amount : '100',
                                buyer_email : 'iamport@siot.do',
                                buyer_name: '구매자이름',
                                buyer_tel : '010-1234-5678',
                                buyer_addr : '서울특별시 강남구 삼성동',
                                buyer_postcode : '123-456',
                            }, function(rsp) {
                                console.log(rsp);
                                if(rsp.success) {
                                    alert('결제 완료');
                                    location.href="st";
                                    $.ajax({
                                        type: 'post',
                                        url: 'procc',
                                        data: {
                                            "hNum" : hall, //상영관 번호
                                            "uid":uid, //uid
                                            "seats":seatInfo, //좌석 => seatInfo
                                            "mcode":mCode, //mcode
                                            "date":date, //날짜
                                            "pcode1":pcode1, //p_code
                                            "pcode2":pcode2,
                                            "time":time //시간
                                        },
                                    });
                                }else{
                                    let msg = '결제 실패';
                                }
                            });
                        }else{
                            if(seatarr[0] == null){
                                alert('좌석을 선택해주세요');
                            }
                            if(num == 0 || !num){
                                alert('인원을 선택해주세요');
                            }
                        }
                    });
                });
            </script>

            <div class="box-payinfo">
                <div class="box-pay-num">
                    <div class="box-teen-num">
                        <h2>청소년</h2>
                        <input type="text" id="teenNum" disabled/>
                        <input type="hidden" id="userId" value="<%=id%>"/>
                    </div>
                    <div class="box-adult-num">
                        <h2>성인</h2>
                        <input type="text" id="adultNum" disabled/>
                    </div>
                    <div class="box-total-num">
                        <h2>총 인원</h2>
                        <input type="text" id="totalNum" disabled/>
                    </div>
                </div>
                <div class="box-pay-select">
                    <div class="box-pay-teen">
                        <input type="text" value="청소년" disabled><br>
                        <select id="teenPrice" name="teenPrice" size="4" onchange="chanfunc();">
                            <option value="0">0</option>
                            <option value="${pList[0]}">1</option>
                            <option value="${pList[0]*2}">2</option>
                            <option value="${pList[0]*3}">3</option>
                            <option value="${pList[0]*4}">4</option>
                        </select>
                    </div>
                    <div class="box-pay-adult">
                        <input type="text" value="성인" disabled><br>
                        <select id="adultPrice" name="adultPrice" size="5" onchange="chanfunc();">
                            <option value="0">0</option>
                            <option value="${pList[1]}">1</option>
                            <option value="${pList[1]*2}">2</option>
                            <option value="${pList[1]*3}">3</option>
                            <option value="${pList[1]*4}">4</option>
                        </select>
                    </div>
                </div>
            </div>
            <script>
                function chanfunc() {
                    let teenNumber = $("#teenNum"); let adultNumber = $("#adultNum"); let totalNumber = $("#totalNum");
                    //인원수 불러옴 (MAX : 4명)
                    let tNum = $("#teenPrice option:selected").text(); let aNum = $("#adultPrice option:selected").text();
                    teenNumber.val(tNum); adultNumber.val(aNum);
                    let total = Number(tNum) + Number(aNum); totalNumber.val(total);
                    let teenPrice = $("#tPrice"); let adultPrice = $("#aPrice"); let totalprice = $("#totalPrice");
                    let hprice1 = $("#htprice"); let hprice2 = $("#haprice"); let htotalprice = $("#htotalprice");
                    //가격

                    let tPrice = $("#teenPrice option:selected").val(); let aPrice = $("#adultPrice option:selected").val();
                    teenPrice.val(tPrice); adultPrice.val(aPrice);
                    hprice1.val(tPrice); hprice2.val(aPrice);

                    let teenagerPrice = Number(hprice1.val()); let AdultPrice = Number(hprice2.val()); let totalSum = teenagerPrice + AdultPrice;
                    totalprice.val(totalSum); htotalprice.val(totalSum);

                    if (total > 4) {
                        alert("최대 4명까지 선택 가능합니다");
                        teenNumber.val(null); adultNumber.val(null); totalNumber.val(null);
                        //가격
                        teenPrice.val(null); adultPrice.val(null); totalprice.val(null);
                        location.reload();
                        $('#teenPrice').focus();
                    } }
            </script>

            <div class="box-pay">
                <div class="box-pay-info">
                    <table>
                        <tr>
                            <td>청소년</td>
                            <td><input type="text" id="tPrice" disabled>원</td>
                            <input type="hidden" id="htprice">
                        </tr>
                        <tr>
                            <td>성인</td>
                            <td><input type="text" id="aPrice" disabled>원</td>
                            <input type="hidden" id="haprice">
                        </tr>
                    </table>
                    <h2>총 가격</h2>
                    <input type="text" id="totalPrice" disabled>
                    <input type="hidden" id="htotalprice">
                </div>
                <input type="button" class="btn-pay" id="reserveBtn" value="결제하기"/>
            </div>
        </div>
        <div class="wrapper-right">
            <div class="mainScreen">
                <h1>SCREEN</h1>
            </div>
            <div id="seatcase">
                <input type="hidden" class="bookedSeatList" id="bookedSeatList" value="${booked}">
                <c:forEach items="${sInfo}" var="sData" varStatus="status">
                    <input type="checkbox" class="hseat" id="${sData.se_code}" name ="hseat" value="${sData.se_code}" >
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<div>

</div>
</body>
</html>
