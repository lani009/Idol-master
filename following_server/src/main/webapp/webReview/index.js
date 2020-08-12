function index() {
    let placeName = document.getElementById("place").value;
    let summery = document.getElementById("summery").value;
    let review = document.getElementById("review").value;
    let id = document.getElementById("id").value;

    let request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            alert("전송 완료");
        } else {
            alert("전송 실패");
            console.log(this.responseText);
        }
    }
    request.open("GET", "http://101.101.208.218:8080/review/set");
    request.setRequestHeader("place", encodeURIComponent(placeName));
    request.setRequestHeader("summery", encodeURIComponent(summery));
    request.setRequestHeader("content", encodeURIComponent(review));
    request.setRequestHeader("id", id);
    request.send();
}