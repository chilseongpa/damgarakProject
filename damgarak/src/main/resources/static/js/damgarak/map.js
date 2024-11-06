function initMap() {
  const centerLocation = { lat: 37.4986, lng: 127.0325 };
  
  const map = new google.maps.Map(document.getElementById("map"), {
    zoom: 7,
    center: centerLocation,
  });

  const markerLocations = [
    { lat: 37.8930, lng: 127.7292, title: "위치 1" },
    { lat: 38.9995, lng: 125.7518, title: "위치 2" },
    { lat: 37.4986, lng: 127.0325, title: "강남역" },
    { lat: 31.3912, lng: 130.8777, title: "위치 3" },
    { lat: 40.75535202026367, lng: -73.98571014404297, title: "뉴욕" } // 제목 추가
  ];

  markerLocations.forEach((location) => {
    new google.maps.Marker({
      position: { lat: location.lat, lng: location.lng },
      map: map,
      title: location.title || "위치", // 제목이 없으면 기본 제목 사용
    });
  });
}
