<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>JARDIN SHOP</title>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="description" content="JARDIN SHOP" />
<meta name="keywords" content="JARDIN SHOP" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scaleable=no" />
<link rel="stylesheet" type="text/css" href="css/reset.css?v=Y" />
<link rel="stylesheet" type="text/css" href="css/layout.css?v=Y" />
<link rel="stylesheet" type="text/css" href="css/content.css?v=Y" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/top_navi.js"></script>
<script type="text/javascript" src="js/left_navi.js"></script>
<script type="text/javascript" src="js/main.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="js/idangerous.swiper-2.1.min.js"></script>
<script type="text/javascript" src="js/jquery.anchor.js"></script>
<!--[if lt IE 9]>
<script type="text/javascript" src="js/html5.js"></script>
<script type="text/javascript" src="js/respond.min.js"></script>
<![endif]-->
<script type="text/javascript">
	$(document).ready(function() {
		
		replyList('${eventView.eventNo}');
		
	});

	//댓글 리스트 불러오기
	function replyList(eventNo){

	$.ajax({
		type:'post',
		url:'./replyList',
		data:{
			eventNo:eventNo
		},
		success:function(data){
			
			var html="";
			
			for(var i=0;i<data.length;i++){
		html+='<form name="replyForm" id="replyFrom">';
		html+='<ul>';
		html+='<li class="name">'+data[i].memId+ '<span>['+data[i].replyDate+']</span></li>';
		html+='<li class="txt">'+data[i].content+'</li>';
		html+='<li class="btn '+data[i].replyNo+'"><a href="javascript:;" class="modib rebtn" onclick="replymodi('+data[i].replyNo+')">수정</a> <a href="javascript:;" onclick=deleteConfirm('+data[i].replyNo+') class="rebtn">삭제</a></li>';
		html+='</ul>';
		html+='<ul id="'+data[i].replyNo+'" class="modiwzone" style="display: none;">';
		html+='<li class="name">'+data[i].memId+'<span>['+data[i].replyDate+']</span></li>';
		html+='<li class="txt"><textarea name="content" id="content'+data[i].replyNo+'" class="replyType">'+data[i].content+'</textarea><input type="text" hidden="hidden" value="'+data[i].content+'" name="'+data[i].replyNo+'"></li>';
		html+='<input type="hidden" name="replyNo" value="'+data[i].replyNo+'">';
		html+='<input type="hidden" name="eventNo" value="'+data[i].eventNo+'">';
		html+='<li class="btn"><button href="javascript:;" onclick="replyUpdate(this.form)" class="rebtn">완료</button>';
		html+='<a href="javascript:;" class="rebtn reset_re" onclick=replyReset("'+data[i].replyNo+'")>취소</a></li>';
		html+='</ul>';	
		html+='</form>';
		
			}
			$("#replyList").html(html);	
		},
		error:function(request,status,error){
			alert("리스트 불러오기 실패");
		}
		
	});
	}
	//댓글 삭제
	function deleteConfirm(replyNo){
		if(confirm("정말 삭제하시겠습니까?")==true){
			
			$.ajax({
				type:'post',
				url:'./replyDelete',
				data:{replyNo:replyNo},
				success:function(){
					alert("삭제되었습니다.");
					location.reload();
				},
				error:function(request,status,error){
					alert("실패")
				}
			});
		}else{
			return;
		}
	}
	

	//로그인 체크후 댓글 작성
	function loginCheck() {
		var loginCheck = eventReply.memId.value;
		if (loginCheck == "") {
			alert("로그인후 작성 가능합니다.");
			return false;
		} else {
			//    	  		var bTitle1 = $('#bTitle').val();
			//    	  		var bContent1 = $('#bContent').val();
			//    	  		var bName1 = $('#bName').val();

			$.ajax({
				type : 'post',
				url : './eventReply', //맵핑 명을적어줌
				data : {
					eventNo : $("#eventNo").val(),
					memId : $("#memId").val(), //--->한개씩 받아올 경우
					replyPw : $("#replyPw").val(),
					content : $("#content").val()
				},
				// :$("#ajaxForm").serialize(), //Form에 있는거 모두 가져오고 심을경우  
				//jsp ->controller 보낼 데이터가 있으면 기입

				success : function(data) {//괄호안에 있는 'data'에 값이 담겨있다.
					alert("댓글이 달렸습니다.");
					replyList();

				},
				error : function(request, status, error) {
					alert("실패");
				}

			});
		}

		eventReply.submit();
	}
	
	

	
	//이전글 다음글
	function preNextPost(){
		$.ajax({
			type:'post',
			url:'./preNextPost',
			data:{
				eventNo:$('#eventNo').val(),
			},
			success:function(data){
// 				alert("이전글 다음글 가져오기 성공");
				
// 				var html="";
// 			html+='<tr>';
// 			html+='<th class="pre">PREV</th>';
// 			html+='<td><a href="preNextPost">상품 재입고는 언제 되나요?</a></td>';
// 			html+='<td>&nbsp;</td>';
// 			</tr>

// 			<tr>
// 				<th class="preNextPost">NEXT</th>
// 				<td>다음 글이 없습니다.</td>
// 				<td>&nbsp;</td>
			}
			
		});
		
	}
</script>
<!-- 댓글 수정 제위치로 오게 만들기 -->
<script type="text/javascript">

function replymodi(a){
	
	$(".modiwzone").hide();
	$(".modib").parent().show();
	$("."+a).hide();
	//수정취소일 경우 내용 초기화
	var k=$("input[name="+a+"]").val();
	$("#content"+a).val(k);
	$('#'+a).show();
}
function replyReset(b){
	$(".modib").parent().show();
	$('#'+b).hide();
	$(".replyType").val="";
}

//댓글 수정
function replyUpdate(replyForm){
	var content=replyForm.content.value;
	var replyNo=replyForm.replyNo.value;
	var eventNo=replyForm.eventNo.value;
// 		var params=$("form[name=replyForm]").serialize();
	$.ajax({
		type:'post',
		url:'./replyUpdate',
		data:{
			content:content,
			replyNo:replyNo,
			eventNo:eventNo
		},
		success:function(data){
			if(data==1){
			alert("댓글 수정 성공");
			location.reload();
			}else{
			alert("댓글 내용 수정 실패");	
			}
			
		},
		error : function(request, status, error) {
			alert("통신 오류");
		}
	
		
	});
}

</script>
</head>
<body>


	<!--익스레이어팝업-->
	<div id="ieUser" style="display: none">
		<div class="iewrap">
			<p class="img">
				<img src="images/ico/ico_alert.gif" alt="알림" />
			</p>
			<p class="txt">
				IE버전이 낮아 홈페이지 이용에 불편함이 있으므로 <strong>IE9이상이나 다른 브라우저</strong>를 이용해
				주세요.
			</p>
			<ul>
				<li><a
					href="http://windows.microsoft.com/ko-kr/internet-explorer/download-ie"
					target="_blank"><img src="images/ico/ico_ie.gif"
						alt="IE 최신브라우저 다운"></a></li>
				<li><a href="https://www.google.com/intl/ko/chrome/browser"
					target="_blank"><img src="images/ico/ico_chrome.gif"
						alt="IE 최신브라우저 다운"></a></li>
				<li><a href="http://www.mozilla.org/ko/firefox/new"
					target="_blank"><img src="images/ico/ico_mozila.gif"
						alt="MOZILA 최신브라우저 다운"></a></li>
				<li><a href="http://www.apple.com/safari" target="_blank"><img
						src="images/ico/ico_safari.gif" alt="SAFARI 최신브라우저 다운"></a></li>
				<li><a href="http://www.opera.com/ko/o/ie-simple"
					target="_blank"><img src="images/ico/ico_opera.gif"
						alt="OPERA 최신브라우저 다운"></a></li>
			</ul>
			<p class="btn" onclick="msiehide();">
				<img src="images/ico/ico_close.gif" alt="닫기" />
			</p>
		</div>
	</div>
	<!--//익스레이어팝업-->
	<!--IE 6,7,8 사용자에게 브라우저 업데이터 설명 Div 관련 스크립트-->
	<script type="text/javascript">
		var settimediv = 200000; //지속시간(1000= 1초)
		var msietimer;

		$(document).ready(function() {
			msiecheck();
		});

		var msiecheck = function() {
			var browser = navigator.userAgent.toLowerCase();
			if (browser.indexOf('msie 6') != -1
					|| browser.indexOf('msie 7') != -1
					|| browser.indexOf('msie 8') != -1) {
				msieshow();
			} else {
				msiehide();
			}
		}

		var msieshow = function() {
			$("#ieUser").show();
			msietimer = setTimeout("msiehide()", settimediv);
		}

		var msiehide = function() {
			$("#ieUser").hide();
			clearTimeout(msietimer);
		}

	</script>

	<div id="allwrap">
		<div id="wrap">

			<div id="header">

				<div id="snbBox">
					<h1>
						<img src="images/txt/logo.gif" alt="JARDIN SHOP" />
					</h1>
					<div id="quickmenu">
						<div id="mnaviOpen">
							<img src="images/btn/btn_mnavi.gif" width="33" height="31"
								alt="메뉴열기" />
						</div>
						<div id="mnaviClose">
							<img src="images/btn/btn_mnavi_close.gif" width="44" height="43"
								alt="메뉴닫기" />
						</div>
						<ul>
							<li><a href="event">EVENT</a></li>
							<li><a href="#">CUSTOMER</a></li>
							<li><a href="#">COMMUNITY</a></li>
						</ul>
					</div>
					<div id="snb">
						<ul>
							<li><a href="login">LOGIN</a></li>
							<li><a href="join">JOIN</a></li>
							<li><a href="#">MY PAGE</a></li>
							<li><a href="#">CART</a></li>
						</ul>

						<div id="search">
							<input type="text" class="searchType" /> <input type="image"
								src="images/btn/btn_main_search.gif" width="23" height="20"
								alt="검색하기" />
						</div>
					</div>
				</div>
			</div>


			<!-- GNB -->
			<div id="gnb">

				<div id="top">
					<ul>
						<li class="brand t1"><a href="#" id="topNavi1">JARDIN’s
								BRAND</a>
							<ul id="topSubm1">
								<li><a href="#">클래스</a></li>
								<li><a href="#">홈스타일 카페모리</a></li>
								<li><a href="#">드립커피백</a></li>
								<li><a href="#">카페리얼 커피</a></li>
								<li><a href="#">오리지널커피백</a></li>
								<li><a href="#">카페리얼 음료</a></li>
								<li><a href="#">마일드커피백</a></li>
								<li><a href="#">워터커피</a></li>
								<li><a href="#">카페포드</a></li>
								<li><a href="#">모히또파티</a></li>
								<li><a href="#">테이크아웃 카페모리</a></li>
								<li><a href="#">포타제</a></li>
							</ul></li>
						<li class="t2"><a href="#" id="topNavi2">원두</a>
							<ul id="topSubm2">
								<li><a href="#">클래스</a></li>
								<li><a href="#">로스터리샵</a></li>
								<li><a href="#">커피휘엘</a></li>
								<li><a href="#">산지별 생두</a></li>
							</ul></li>
						<li class="t1"><a href="#" id="topNavi3">원두커피백</a>
							<ul id="topSubm3">
								<li><a href="#">드립커피 로스트</a></li>
								<li><a href="#">오리지널커피백</a></li>
								<li><a href="#">마일드커피백</a></li>
							</ul></li>
						<li class="t2"><a href="#" id="topNavi4">인스턴트</a>
							<ul id="topSubm4">
								<li><a href="#">까페모리</a></li>
								<li><a href="#">홈스타일카페모리</a></li>
								<li><a href="#">포타제</a></li>
							</ul></li>
						<li class="t1"><a href="#" id="topNavi5">음료</a>
							<ul id="topSubm5">
								<li><a href="#">까페리얼</a></li>
								<li><a href="#">워터커피</a></li>
								<li><a href="#">모히또</a></li>
							</ul></li>
						<li class="t2"><a href="#" id="topNavi6">커피용품</a>
							<ul id="topSubm6">
								<li><a href="#">종이컵</a></li>
								<li><a href="#">커피필터</a></li>
								<li><a href="#">머신 등</a></li>
							</ul></li>
						<li class="t1"><a href="#" id="topNavi7">선물세트</a></li>
						<li class="t2"><a href="#" id="topNavi8">대량구매</a></li>
					</ul>
				</div>

				<script type="text/javascript">
					initTopMenu();
				</script>
			</div>
			<!-- //GNB -->

			<!-- container -->
			<div id="container">

				<div id="location">
					<ol>
						<li><a href="#">HOME</a></li>
						<li><a href="#">EVENT</a></li>
						<li class="last">진행중 이벤트</li>
					</ol>
				</div>

				<div id="outbox">
					<div id="left">
						<div id="title2">
							EVENT<span>이벤트</span>
						</div>
						<ul>
							<li><a href="#" id="leftNavi1">진행중 이벤트</a></li>
							<li><a href="#" id="leftNavi2">종료된 이벤트</a></li>
							<li class="last"><a href="#" id="leftNavi3">당첨자 발표</span></a></li>
						</ul>
					</div>
					<script type="text/javascript">
						initSubmenu(1, 0);
					</script>


					<!-- contents -->
					<div id="contents">
						<div id="mypage">
							<h2>
								<strong>진행중 이벤트</strong><span>쟈뎅샵의 특별한 혜택이 가득한 이벤트에 참여해
									보세요.</span>
							</h2>

							<div class="viewDivMt">
								<div class="viewHead">
									<div class="subject">
										<ul>
											<li>${eventView.title }</li>
										</ul>
									</div>
									<div class="day">
										<p class="txt">
											이벤트 기간 <span><fmt:formatDate
													value="${eventView.startDate}" pattern="yyyy-MM-dd" /> ~ <fmt:formatDate
													value="${eventView.endDate}" pattern="yyyy-MM-dd" /></span>
										</p>
									</div>
								</div>

								<div class="viewContents">
									<img src="resources/eventImage/${eventView.eventImage}" alt="" />
								</div>
							</div>


							<!-- 이전다음글 -->
							<div class="pnDiv web">
								<table summary="이전다음글을 선택하여 보실 수 있습니다." class="preNext"
									border="1" cellspacing="0">
									<caption>이전다음글</caption>
									<colgroup>
										<col width="100px" />
										<col width="*" />
										<col width="100px" />
									</colgroup>
									<tbody id="prePost">
<!-- 										<tr> -->
<!-- 											<th class="pre">PREV</th> -->
<!-- 											<td><a href="preNextPost">상품 재입고는 언제 되나요?</a></td> -->
<!-- 											<td>&nbsp;</td> -->
<!-- 										</tr> -->

<!-- 										<tr> -->
<!-- 											<th class="preNextPost">NEXT</th> -->
<!-- 											<td>다음 글이 없습니다.</td> -->
<!-- 											<td>&nbsp;</td> -->
										</tr>
									</tbody>
								</table>
							</div>
							<!-- //이전다음글 -->

							<form name="eventReply" id="eventReply" method="post">
								<!-- 댓글-->
								<div class="replyWrite">
									<ul>
										<li class="in">
											<p class="txt">
												총 <span class="orange">3</span> 개의 댓글이 달려있습니다.
											</p>
											<p class="password">
												비밀번호&nbsp;&nbsp;<input type="password" name="replyPw"
													id="replyPw" class="replynum" />
											</p> <textarea class="replyType" id="content" name="content"></textarea>
											<input type="hidden" name="eventNo" id="eventNo"
											value="${eventView.eventNo}" /> <input type="hidden"
											name="memId" id="memId" value="${memId}" />
										</li>
										<li class="btn"><button type="button"
												onclick="loginCheck()" class="replyBtn">등록</button></li>
									</ul>
									<p class="ntic">※ 비밀번호를 입력하시면 댓글이 비밀글로 등록 됩니다.</p>
								</div>
							</form>
							<div class="replyBox" id="replyList">

<!-- 								<ul> -->
<!-- 									<li class="name">jjabcde <span>[2014-03-04&nbsp;&nbsp;15:01:59]</span></li> -->
<!-- 									<li class="txt">대박!!! 이거 저한테 완전 필요한 이벤트였어요!!</li> -->
<!-- 									<li class="btn"><a href="javascript:;" -->
<!-- 										class="rebtn modib">수정</a> <a href="javascript:;" -->
<!-- 										class="rebtn">삭제</a></li> -->
<!-- 								</ul> -->
<!-- 								<ul class="modiwzone" style="display: none;"> -->
<!-- 									<li class="name">jjabcde <span>[2014-03-04&nbsp;&nbsp;15:01:59]</span></li> -->
<!-- 									<li class="txt"><textarea class="replyType"></textarea></li> -->
<!-- 									<li class="btn"><a href="javascript:;" class="rebtn">완료</a> -->
<!-- 										<a href="javascript:;" class="rebtn reset_re">취소</a></li> -->
<!-- 								</ul> -->
<!-- 								<ul> -->
<!-- 									<li class="name">jjabcde <span>[2014-03-04&nbsp;&nbsp;15:01:59]</span></li> -->
<!-- 									<li class="txt">대박!!! 이거 저한테 완전 필요한 이벤트였어요!!</li> -->
<!-- 									<li class="btn"><a href="javascript:;" -->
<!-- 										class="rebtn modib">수정</a> <a href="javascript:;" -->
<!-- 										class="rebtn">삭제</a></li> -->
<!-- 								</ul> -->
<!-- 								<ul class="modiwzone" style="display: none;"> -->
<!-- 									<li class="name">jjabcde <span>[2014-03-04&nbsp;&nbsp;15:01:59]</span></li> -->
<!-- 									<li class="txt"><textarea class="replyType"></textarea></li> -->
<!-- 									<li class="btn"><a href="javascript:;" class="rebtn">완료</a> -->
<!-- 										<a href="javascript:;" class="rebtn reset_re">취소</a></li> -->
<!-- 								</ul> -->
							</div>
							<!-- //댓글 -->


							<!-- Btn Area -->
							<div class="btnArea">
								<div class="bRight">
									<ul>
										<li><a href="event" class="sbtnMini mw">목록</a></li>
									</ul>
								</div>
							</div>
							<!-- //Btn Area -->

						</div>
					</div>
					<!-- //contents -->


					<script type="text/javascript"
						src="js/jquery.fancybox-1.3.4.pack.js"></script>
					<link rel="stylesheet" type="text/css"
						href="css/jquery.fancybox-1.3.4.css" />
					<script type="text/javascript">
						$(function() {

							var winWidth = $(window).width();
							if (winWidth > 767) {
								var layerCheck = 540;
							} else {
								var layerCheck = 320;
							}

							$(".passwordBtn")
									.fancybox(
											{
												'autoDimensions' : false,
												'showCloseButton' : false,
												'width' : layerCheck,
												'padding' : 0,
												'type' : 'iframe',
												'onComplete' : function() {
													$('#fancybox-frame')
															.load(
																	function() { // wait for frame to load and then gets it's height
																		$(
																				'#fancybox-content')
																				.height(
																						$(
																								this)
																								.contents()
																								.find(
																										'body')
																								.height());
																	});
												}
											});

						});
					</script>

				</div>
			</div>
			<!-- //container -->




			<div id="footerWrap">
				<div id="footer">
					<div id="fnb">
						<ul>
							<li class="left"><a href="#">개인정보취급방침</a></li>
							<li><a href="#">이용약관</a></li>
							<li class="left"><a href="#">이메일무단수집거부</a></li>
							<li><a href="#">고객센터</a></li>
							<li class="left brand"><a href="#">쟈뎅 브랜드 사이트</a></li>
						</ul>
					</div>

					<div id="finfo">
						<div id="flogo">
							<img src="images/txt/flogo.gif"
								alt="JARDIN THE COFFEE CREATOR, SINCE 1984" />
						</div>
						<address>
							<ul>
								<li>㈜쟈뎅</li>
								<li>대표자 윤영노</li>
								<li class="tnone">주소 서울시 강남구 논현동 4-21번지 영 빌딩</li>
								<li class="webnone">소비자상담실 02)546-3881</li>
								<li>사업자등록번호 211-81-24727</li>
								<li class="tnone">통신판매신고 제 강남 – 1160호</li>
								<li class="copy">COPYRIGHT © 2014 JARDIN <span>ALL
										RIGHTS RESERVED.</span></li>
							</ul>
						</address>

						<div id="inicis">
							<img src="images/ico/ico_inicis.png" alt="이니시스 결제시스템" />
						</div>
					</div>
				</div>
			</div>



		</div>
	</div>
</body>
</html>