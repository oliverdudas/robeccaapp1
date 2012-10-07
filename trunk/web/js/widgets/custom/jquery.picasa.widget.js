(function($) {

    var PicasaWidget = {

        // These options will be used as defaults
        options: {
            picasaUserId: "106685618427620984651",
            picasaAlbumId: "5667859215738016225",
            bigThumbSize: 512,
            littleThumbSize: 104,
            imgmax: 1280,
            clear: null,
            albumSizeSuccess: null,
            start: null,
            end: null
        },

        _albumsURI: function() {
            var self = this;
            return "http://picasaweb.google.com/data/feed/api/"
                    + "user/" + self.options.picasaUserId
                    + "?alt=" + "json"
                    + "&access=visible"
                    + "&v=2"
                    + "&kind=" + "album"
                    + "&hl=" + "en_US"
                    + "&fields=" + "entry(media:group,id,gphoto:id,gphoto:numphotos)"
                    + "&thumbsize=" + self.options.bigThumbSize + ',' + self.options.littleThumbSize
                    + "&callback=?";
        },

        _albumsSizeURI: function() {
            var self = this;
            return "http://picasaweb.google.com/data/feed/api/"
                    + "user/" + self.options.picasaUserId
                    + "?alt=" + "json"
                    + "&access=visible"
                    + "&v=2"
                    + "&kind=" + "album"
                    + "&hl=" + "en_US"
                    + "&fields=" + "entry(gphoto:id,gphoto:numphotos)"
                    + "&callback=?";

        },

        _albumPhotosURI: function() {
            var self = this;
            return "http://picasaweb.google.com/data/feed/api/"
                    + "user/" + self.options.picasaUserId
                    + "/albumid/" + self.options.picasaAlbumId
                    + "?alt=" + "json"
                    + "&access=visible"
                    + "&v=2"
                    + "&kind=" + "photo"
                    + "&hl=" + "en_US"
                    + "&fields=" + "entry(id,content,media:group,gphoto:numphotos)"
                    + "&thumbsize=" + self.options.littleThumbSize
                    + "&imgmax=" + self.options.imgmax
                    + "&callback=?";
        },

        // Set up the widget
        _create: function() {
            var self = this;

            self._bindEvents();

            self._trigger('start');

            self._requestAlbumSize();

        },

        _bindEvents: function() {
            var self = this;

            self.options.albumSizeSuccess = function(e, albumSize) {
                self._requestAlbumPhotos();
            };
        },

        _requestAlbumSize: function() {
            var self = this;

            $.ajax({
                type: 'GET',
                cache: false,
                url: self._albumsSizeURI(),
                success : function(data, textStatus, jqXHR) {

                    var photosNumber = null;

                    $.each(data.feed.entry, function(entryIndex, item) {

                        var album_ID = item.gphoto$id.$t;
                        photosNumber = item.gphoto$numphotos.$t;

                        if (album_ID == self.options.picasaAlbumId) {
                            for (var i = 0; i < photosNumber; i++) {
                                self._appendPhotoFrame(self.element);
                            }
                            return false;
                        }
                    });

                    self._trigger('albumSizeSuccess', 0, photosNumber);

                },
                error : function(jqXHR, textStatus, errorThrown) {
                    alert(errorThrown);
                },
                dataType: 'json',
                async: true

            });

        },

        _appendPhotoFrame: function(targetElem) {
            var frame = $('<span class="photoframe"></span>');
            $(frame).css({
                width: '104px',
                height: '104px',
                margin: '0 2px',
                display: 'inline-block'
            });
            $(targetElem).append(frame);
        },

        _requestAlbumPhotos: function() {
            var self = this;

            var photoFrames = self.element.find('span.photoframe');

            $.ajax({
                type: 'GET',
                cache: false,
                url: self._albumPhotosURI(),
                success : function(data) {
                    $.each(data.feed.entry, function(entryIndex, entry) {
                        var contentUrl = entry.media$group.media$content[0].url;
                        var caption = entry.media$group.media$description.$t;
                        var anchor = $('<a href=""></a>');
                        anchor.attr('href', contentUrl);
                        anchor.attr('title', caption);
                        anchor.addClass('galleryA');
                        $(photoFrames[entryIndex]).append(anchor);
                        //Thumbnail URL
                        $.each(entry.media$group.media$thumbnail, function(thumbnailIndex, thumbnail) {
                            var photoThumbUrl = thumbnail.url;
                            var img = new Image();
                            $(img).load(
                                    function () {
                                        $(this).hide();
                                        anchor.append(this);
                                        $(this).fadeIn(1000);
                                    }).error(
                                    function () {
                                        alert('Image ' + photoThumbUrl + 'could not be loaded!');
                                    }).attr({'src': photoThumbUrl}).css({'display': 'inline'});

                        });
                    });

                    self._trigger('end');
                },
                dataType: 'json',
                async: false

            });
        },

        // Use the _setOption method to respond to changes to options
        _setOption: function(key, value) {
            switch (key) {
                case "clear":
                    // handle changes to clear option
                    break;
            }

            // In jQuery UI 1.8, you have to manually invoke the _setOption method from the base widget
            $.Widget.prototype._setOption.apply(this, arguments);
            // In jQuery UI 1.9 and above, you use the _super method instead
            // this._super( "_setOption", key, value );

        },

        // Use the destroy method to clean up any modifications your widget has made to the DOM
        destroy: function() {
            // In jQuery UI 1.8, you must invoke the destroy method from the base widget
            $.Widget.prototype.destroy.call(this);
            // In jQuery UI 1.9 and above, you would define _destroy instead of destroy and not call the base method
        }

    };

    $.widget("wg.picasa", PicasaWidget);

}(jQuery) );

