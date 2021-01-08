window.redirectsTag = {};
redirectsTag.__renderTag = function (response) {
    if (response && response.data) {
        var el = this.getElement();
        el.innerHTML = response.data;
        var scripts = this.getElement().getElementsByTagName("script");
        for (var i = 0; i < scripts.length; i++) {
            if (scripts[i].src != "") {
                var tag = document.createElement("script");
                tag.src = scripts[i].src;
                document.getElementsByTagName("head")[0].appendChild(tag)
            } else {
                eval(scripts[i].innerHTML)
            }
        }
    }
};
var divName = "redirectsTag";
redirectsTag.getElement = function () {
    var el = document.getElementById(divName);
    if (el) {
        return el
    } else {
        el = document.createElement("div");
        el.id = divName;
        var body = document.getElementsByTagName("body")[0];
        body.appendChild(el);
        return el
    }
};
redirectsTag.__renderTag({data: '%1s'});
