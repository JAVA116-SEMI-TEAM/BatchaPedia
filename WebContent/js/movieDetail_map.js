
<script type="text/javascript">
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
mapOption = { 
    center: new kakao.maps.LatLng(37.4986855,127.0297111), // 지도의 중심좌표
    level: 3 // 지도의 확대 레벨
    
};

var map = new kakao.maps.Map(mapContainer, mapOption); //지도를 생성
//마커가 표시될 위치
var markerPosition  = new kakao.maps.LatLng(37.4986855,127.0297111); 
//마커 생성
var marker = new kakao.maps.Marker({
position: markerPosition
});

//마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);
</script>