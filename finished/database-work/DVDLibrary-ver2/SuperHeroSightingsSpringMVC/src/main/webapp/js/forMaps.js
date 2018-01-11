    function initMap(){
        var lati = "<c:out value='${lvm.location.latitude}'/>";
        var long = "<c:out value='${lvm.location.longitude}'/>";
                        
                        
        var map = new google.maps.Map(document.getElementById("map"),
        {zoom: 15, center: new google.maps.LatLng(lati, long)});
        var marker = new google.maps.Marker({
           position: {lat: <c:out value='${lvm.location.latitude}'/>, lng: <c:out value='${lvm.location.longitude}'/>},
           map: map
        });
    }
