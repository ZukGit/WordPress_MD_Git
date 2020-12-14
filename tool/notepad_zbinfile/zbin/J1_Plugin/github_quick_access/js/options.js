; (function () {

    init();

    async function init() {
        //获取缓存数据
        let input_url_checkbox = await asyncGetItem("input_url_checkbox");
        let open_mix_model = await asyncGetItem("open_mix_model");
        let cf_url = await asyncGetItem("cf_url");
        let has_cf_url = cf_url && isUrl(cf_url);
        let close_yidian = await asyncGetItem("close_yidian");

        if (parseInt(input_url_checkbox)) {
            $("#input_url").attr('disabled', false);
            $("#input_url_checkbox").attr("checked", true);
        } else {
            $("#input_url").attr('disabled', true);
            $("#input_url_checkbox").attr("checked", false);
        }

        if (parseInt(close_yidian)) {
            $("#close_yidian").attr("checked", true);
        } else {
            $("#close_yidian").attr("checked", false);
        }

        if (parseInt(open_mix_model)) {
            $("#open_mix_model").attr("checked", true);
        } else {
            $("#open_mix_model").attr("checked", false);
        }


        if (has_cf_url) {
            $("#input_url").val(cf_url);
        }


        $("#input_url_checkbox").on('change', function () {
            let checked = $(this).is(':checked');

            if (checked) {
                $("#input_url").attr('disabled', false);
                setItemByKey("input_url_checkbox", 1);
            } else {
                $("#input_url").attr('disabled', true);
                setItemByKey("input_url_checkbox", 0);
            }
        });

        $("#open_mix_model").on('change', function () {
            let checked = $(this).is(':checked');

            if (checked) {
                setItemByKey("open_mix_model", 1);
            } else {
                setItemByKey("open_mix_model", 0);
            }
        });

        $("#close_yidian").on('change', function () {
            let checked = $(this).is(':checked');

            if (checked) {
                setItemByKey("close_yidian", 1);
            } else {
                setItemByKey("close_yidian", 0);
            }
        });

        $("#save_btn").on('click', function () {

            let checked = $("#input_url_checkbox").is(":checked");
            let yidian = $("#close_yidian").is(":checked");
            let url = $("#input_url").val();

            if (checked && (url !== "")) {
                $("#input_url_checkbox").attr("checked", true);
                setItemByKey("input_url_checkbox", 1);
                setItemByKey("cf_url", url);
            }

            if (checked && (url == "")) {
                alert("请填写加速网址");
                return false;
            }

            if (yidian) {
                setItemByKey("close_yidian", 1);
            } else {
                setItemByKey("close_yidian", 0);
            }

            alert("保存成功");
        });
    }
})()