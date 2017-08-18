<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/template/taglib.jsp" %>
<!DOCTYPE html>
<html lang="en">

	<head>
		<meta charset="utf-8">
		<title>WawajobsChina</title>
		<meta name="keywords" content="resume,jobs,,job search,find jobs,recruit,WawajobsChina">
		<meta http-equiv="Window-target"  content="_top">
		<meta name="x5-orientation" content="portrait">
		<meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
		<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/swiper.min.css"/>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/index.css"/>
		<!-- Demo styles -->
		<style>
			html,body {
				position: relative;
				height: 100%;
			}
			
			.swiper-container3 {
				width: 100%;
				height: 100%;
				/*给类名加完下标后不加overflow会出现滚动条*/
				overflow: hidden;
			}
			
			.swiper-slide {
				/* Center slide text vertically */
				/*display: -webkit-box;
				display: -ms-flexbox;
				display: -webkit-flex;
				display: flex;
				-webkit-box-pack: center;
				-ms-flex-pack: center;
				-webkit-justify-content: center;
				justify-content: center;
				-webkit-box-align: center;
				-ms-flex-align: center;
				-webkit-align-items: center;
				align-items: center;*/
			}
		</style>
	</head>

	<body>
		<!-- Swiper -->
		<div class="swiper-container3">
			<div class="swiper-wrapper">
				<!--第一屏-->
				<div class="swiper-slide">
					<!--logo-->
					<div class="logoBox">
						<div class="up">
							<img src="${pageContext.request.contextPath}/static/img/logo.png" class="logo"/>
							<div class="signAndLog">
							<c:if test="${not empty sessionScope.SPRING_SECURITY_CONTEXT}">
								${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.email}
								<span id="Login" onclick="window.location.href='${pageContext.request.contextPath}/logout'">
									Log out
								</span>
							</c:if>
							<c:if test="${empty sessionScope.SPRING_SECURITY_CONTEXT}">
								<span id="Login" onclick="window.location.href='${pageContext.request.contextPath}/login.jsp'">
									Log in
								</span>
							</c:if>
								&nbsp;&nbsp;/&nbsp;&nbsp;
								<span id="Signup"  onclick="window.location.href='${pageContext.request.contextPath}/public/user/reg/page'">
									Sign up
								</span>
								
							</div>
						</div>
						<!--<ul class="down">
							<li class="first">HOME</li>
							<li>TEACH</li>
							<li>INTERNS</li>
							<li>HOMESTAY</li>
							<li>MANDRAIN</li>
							<li>MEDIA</li>
							<li>TESTIMONIALS</li>
							<li>ABOUTUS</li>
						</ul>-->
					</div>
					
					<!--banner-->
					<div class="banner">
						<img class="imgBanner" src="${pageContext.request.contextPath}/static/img/banner.jpg"/>
						<div id="con">
							<p class="con1">Immerse Yourself Abroad</p>
							<p class="con2">Get your portfolio up and running no time.</p>
							<p class="con3"><a href="javascript;">  Read more</a></p>
						</div>
					</div>
				</div>
				
				<!--第二屏-->
				<div class="swiper-slide">
					<!--lun2-->
					<div class="lun2Box">
						<div class="up">
							<p class="p1">Who is WawajobsChina?</p>
							<p class="p2">Hi, we are WawajobsChina Members. Our pleasure to introduce you our programes.We reflect our vision to bridge the gap between China and abroad.</p>
						</div>
						<div class="down" id="down">
							<img style="z-index: 1;opacity: 1;" src="${pageContext.request.contextPath}/static/img/lun2-1.png" class="down1"/>
							<img src="${pageContext.request.contextPath}/static/img/lun2-2.png" class="down2"/>
						</div>
					</div>
				</div>
				
				<!--第三屏-->
				<div class="swiper-slide">
					<!--lun3-->
					<div class="lun3Box swiper-container">
						<div class="swiper-wrapper">					
				            <div class="swiper-slide slide1">
				            	<div class="Pbox">
				            		<p class="title">Teaching</p>
				            		<div class="teaDown">
				            			<p class="con4 conP1">Placement in Chinese schools to experience complete cultural immersion into local society.</p>
						            	<p class="conP2"><span>--Helped Hundred of people truly experience China</span><em>--High Allowance</em></p>
						            	<p class="conP1 conP3">--Free Accommodation <span>--Professional VISA Support</span>--See More Benefits</p>
				            		</div>
				            	</div>
				            	<img src="${pageContext.request.contextPath}/static/img/lun3-1.png" class="slide1Img"/>
				            </div>
				            <div class="swiper-slide slide2">
				            	<div class="Pbox">
				            		<p class="title">Internship</p>
				            		<div class="teaDown">
				            			<p class="conP1 con4">Earning aboard cultural skills and building career abundant.</p>
						            	<p class="conP1 con2"><span>--Have the opportunity to work in a well-known organizations.</span><em>-Free Accommodatoin</em></p>
						            	<p class="conP1 con3"> <span>--Allowance Support</span>--See More Benefits</p>
						            	<p class="conP1">-Professional Mandarin and Industry Tranning Before Start Internship.</p>
				            		</div>
				            	</div>
				            	<img src="${pageContext.request.contextPath}/static/img/lun3-2.png" class="slide2Img"/>
				            </div>
				            <div class="swiper-slide slide3">
				            	<div class="Pbox">
				            		<p class="title">Home Stay</p>
				            		<div class="teaDown">
					            		<p class="conP1 con4">Stay in a home where there's a host to welcome you and introduce you to the local culture.All our homestays have a host present.</p>
						            	<p class="conP1 con3">-Free Food and Accommodation<span>-Allowance Support</span>-Real Travel Experience</p>
						            	<p class="conP1">-See More Benefits</p>
				            		</div>
				            	</div>
				            	<img src="${pageContext.request.contextPath}/static/img/lun3-3.png" class="slide3Img"/>
				            </div>
				            <div class="swiper-slide slide4">
				            	<div class="Pbox">
				            		<p class="title">Mandarin</p>
				            		<div class="teaDown">
				            			<p class="conP1 con4">Tailored courses for gourps to complement your curriculum.The program content could be academic,eductional or industry specific in focus.</p>
						            	<p class="conP1 con3"><span>-Free Accommodation</span>-Faield of study:Mandarin,Arts,Business,History,social sciences</p>
						            	<p class="conP1"><span>-Programming includes:Company visits,tours,lectures,language instruction</span>-See More Benefits</p>
				            		</div>
				            	</div>
				            	<img src="${pageContext.request.contextPath}/static/img/lun3-4.png" class="slide4Img"/>
				            </div>
				        </div>
				        <!--下边的切换点点-->
				        <div class="swiper-pagination"></div>
					</div>
				</div>
				
				<!--第四屏-->
				<div class="swiper-slide">
					<!--why choose-->
					<div class="whyBox">
						<p class="tit">Why Choose WawajobsChina?</p>
						<p class="con1">We aims to offer its comprehensive educational/work services to its participants at their utmost level of professionalism with an open mind and understanding.</p>
						<div class="sixBox">
							<div class="sixBoxBox">
								<div class="boxAlone Alone1">
									<div class="imgBox">
										<img src="${pageContext.request.contextPath}/static/img/left1.png"/>
									</div>
									<p class="titl">5+ years of experienceCreate</p>
									<p class="AloneCont">MaxWisdom has been successfully offering participants from around the world,career and culture opportunities.</p>
								</div>
								<div class="boxAlone Alone2">
									<div class="imgBox">
										<img src="${pageContext.request.contextPath}/static/img/right1.png"/>
									</div>
									<p class="titl">Participants form 50 countries</p>
									<p class="AloneCont">MaxWisdom has been specializing in creating tailored educational programs to bridge the gap between the East and West.</p>
								</div>
								<div class="boxAlone Alone3">
									<div class="imgBox">
										<img src="${pageContext.request.contextPath}/static/img/left2.png"/>
									</div>
									<p class="titl">500+program Participants</p>
									<p class="AloneCont">MaxWisdom is responsible for bringing thousands of international participants to join our programs.</p>
								</div>
								<div class="boxAlone Alone4">
									<div class="imgBox">
										<img src="${pageContext.request.contextPath}/static/img/right2.png"/>
									</div>
									<p class="titl">Enriched Allowance Provide</p>
									<p class="AloneCont">MaxWisdom provides the most competitive allowance for each of participants. Whatever the program you choose.</p>
								</div>
								<div class="boxAlone Alone5">
									<div class="imgBox">
										<img src="${pageContext.request.contextPath}/static/img/left3.png"/>
									</div>
									<p class="titl">Free Accommodation All Your Trip</p>
									<p class="AloneCont">MaxWisdom has been successfully offering participants from around the world,career and culture opportunities.</p>
								</div>
								<div class="boxAlone Alone6">
									<div class="imgBox">
										<img src="${pageContext.request.contextPath}/static/img/right3.png"/>
									</div>
									<p class="titl">Legal and Safety VISA provide.</p>
									<p class="AloneCont">MaxWisdom has been successfully offering participants from around the world,career and culture opportunities.</p>
								</div>
							</div>
						</div>
					</div>
				</div>
				
				<!--第五屏 how do-->
				<div class="swiper-slide">
					<!--How do-->
					<div class="HowBox">
						<p class="tit">How do I sign up?</p>
						<p class="con1">Create your MaxWisdom menber profile, Choose your desired program, and hand in your application. It's free to join and list your programs.</p>
						
						<div class="circleBox">
							<div class="rightBox1">
								<div class="circleBoxAlone circleBoxAlone1">
									<img src="${pageContext.request.contextPath}/static/img/circle1.png"/>
									<div class="pBox">
										<p>CHOOSE A FAVOURITE PROGRAM</p>
									</div>
									<p>Compare with the each of program's introduction,select your favourite program from the program list.</p>
								</div>
								<img src="${pageContext.request.contextPath}/static/img/right.png" class="right right1"/>
							</div>
							
							<div class="rightBox2">
								<div class="circleBoxAlone circleBoxAlone2">
									<img src="${pageContext.request.contextPath}/static/img/circle2.png"/>
									<div class="pBox">
										<p>CREATE ACCOUNT</p>
									</div>
									<p>You provide some initial detials for your basic profile,then get access to a personal account to complete your program profile.</p>
								</div>
								<img src="${pageContext.request.contextPath}/static/img/right.png" class="right right2"/>
							</div>
							
							<div class="rightBox3">
								<div class="circleBoxAlone circleBoxAlone3" >
									<img src="${pageContext.request.contextPath}/static/img/circle3.png"/>
									<div class="pBox">
										<p>COMPLETE PROFILE</p>
									</div>
									<div class="kuan">
										<p>Only ten simple steps to finish youe application.Clearly instruction will guide you to complete profile step by step.The better your descreption profile,the more positions and opportunties you will receive.</p>
									</div>
								</div>
								<img src="${pageContext.request.contextPath}/static/img/right.png" class="right right3"/>
							</div>
							
							<div class="circleBoxAlone circleBoxAlone4">
								<img src="${pageContext.request.contextPath}/static/img/circle4.png"/>
								<div class="pBox">
									<p>GO LIVE!</p>
								</div>
								<p>We review your program application,you start abroad immersion trip!Click here to see the program detials.</p>
							</div>
						</div>
					</div>
				</div>
				
				<!--第六屏-->
				<div class="swiper-slide">
					<!--contact us-->
					<div class="contactBox">
						<p class="tit">Contact Us</p>
						<p class="con1">If you have any questions, please do not  hesitate to get in touch with us. We are here to help.</p>
						
						<div class="left">
							<form action="#" method="post">
								<p class="left1">
									<input type="text" name="first" id="name" value="" placeholder="Name"/>
									<input type="email" name="Emai" id="emai" value=""placeholder="Email"/>
								</p>
								<p><input type="text" name="tex" id="tex" value="" placeholder="Subject"/></p>
								<div class="textareaBox">
									<textarea name="mess" rows="" cols="" placeholder="Message"></textarea>
								</div>
								<p id="subm"><input type="submit" value="Send Messsge"/></p>
							</form>
						</div>
						
						<div class="right">
							<div class="p1">
								<!--<p class="noneImg">Beijing WawajobsChina Global Ltd</p>-->
							  	<p class="lubiao">
							  		<img src="${pageContext.request.contextPath}/static/img/contact1.png"/>
							  		<span class="biaocenter">jia05121013</span>
							  	</p>
							  	<!--<p class="noneImg">Development Park,</p>
							  	<p class="noneImg">Shunyi District,Beijing China</p>-->
							</div>
							  
							  <div class="p2">
							  	<p class="number number1">
								  	<img src="${pageContext.request.contextPath}/static/img/contact2.png"/>
								  	<span class="biaocenter">WawajobsChina@gmail.com</span>
							  	</p>
							  	<!--<p class="number number2">
								  	<img src="${pageContext.request.contextPath}/static/img/contact3.png"/>
								  	+86 010-8963563
							 	</p>
							  	<p class="number number3">
								  	<img src="${pageContext.request.contextPath}/static/img/contact4.png"/>
								  	58575737@qq.com
							  	</p>-->
							  </div>
							  
						</div>
					</div>
					<!--last-->
					<div class="lastBox">
						<div class="lastBoxCenter">
							<div class="chatBox">
								<div class="left">
									<img class="erma" src="${pageContext.request.contextPath}/static/img/erWeiMa.png"/>
									<span>WECHAT</span>
								</div>
								<div class="right">
									<a  title="" href="skype:b6ff88e1c9f5e117?chat ">
										<img class="aligncenter" src="${pageContext.request.contextPath}/static/img/skype.jpg" alt="Click Me"/>
									</a>
									<!--<img src="img/skype.jpg"/>-->
									<span>SKYPE NUMBER：<br /> WawajobsChina@gmail.com</span>
								</div>
								<!--<span id="lets">Let's socialize:</span>-->
							</div>
							
							<div class="top10">
								<img class="downlogo" src="${pageContext.request.contextPath}/static/img/logo.png"/>
								<!--<img class="shareImg" src="img/share9.png"/>
								<img class="shareImg" src="img/share8.png"/>
								<img class="shareImg" src="img/share7.png"/>
								<img class="shareImg" src="img/share6.png"/>
								<img class="shareImg" src="img/share5.png"/>
								<img class="shareImg" src="img/share4.png"/>
								<img class="shareImg" src="img/share3.png"/>
								<img class="shareImg" src="img/share2.png"/>
								<img class="shareImg" src="img/share1.png"/>-->
							</div>
							<p class="lastP">&copy;Copyright2017<span>&nbsp;&nbsp; WawajobsChina.com/</span></p>
							
						</div>
					</div>
					
				</div>
			</div>
			<!-- Add Pagination -->
			<div class="swiper-pagination"></div>
		</div>

		<script src="${pageContext.request.contextPath}/static/js/resume/swiper.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/jquery/jquery-3.2.1.min.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/resume/move.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/resume/base.js"></script>
		<script src="${pageContext.request.contextPath}/static/js/resume/index.js"></script>
		<script>
			var swiper = new Swiper('.swiper-container3', {
//				pagination: '.swiper-pagination',
				paginationClickable: true,
				mousewheelControl : true,
				direction: 'vertical'
			});
		</script>
	</body>

</html>