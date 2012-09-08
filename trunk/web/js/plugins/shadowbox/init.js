$(function() {

    function setupDemos() {
        Shadowbox.setup("a.galleryA", {
            gallery: "mustang",
            continuous: true,
            counterType: "skip"
        });
    }

    Shadowbox.init({
        overlayOpacity: 0.8
    }, setupDemos);

});