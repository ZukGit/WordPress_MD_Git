; (function () {

    //fastgit.org加速通道
    let fastgithub_url = "https://hub.fastgit.org";
    // cnpmjs.org加速通道
    let cnpmjs_url = "https://github.com.cnpmjs.org";
    // CF加速通道
    let cf_url = "https://github.91chifun.workers.dev/";
    let yidian = `<div class="mt-2 d-flex">
    <a href="https://yidian.one" target="_blank" style="margin:0 auto;font-size:12px;">一点资讯: 简单高效阅读</a>
</div>`;
    let close_yidian;

    //git协议加速通道
    let ssh_url = "git@git.zhlh6.cn:";
    let fast_git_ssh_url = "git@hub.fastgit.org:";

    //要使用的加速通道
    let use_url = cf_url;

    // url
    let url = window.location.href;
    let url_data = url.split("/");
    let github_auth_name = url_data[3];
    let git_name = url_data[4];
    let is_commits = url_data[5] && (url_data[5] == "commits");
    console.log("is_commits: ", is_commits);
    let git_url = '/' + github_auth_name + '/' + git_name + '.git';
    let pathname = window.location.pathname;

    main();

    // new button style:
    // btn ml-2 btn-primary

    // old button style:
    // btn btn-sm ml-2 btn-primary

    //执行
    async function main() {


        //是否使用自定义加速通道
        let input_url_checkbox = await asyncGetItem("input_url_checkbox");
        let new_cf_url = await asyncGetItem("cf_url");
        let open_mix_model = await asyncGetItem("open_mix_model");
        close_yidian = await asyncGetItem("close_yidian");
        close_yidian = parseInt(close_yidian);

        if (input_url_checkbox) {
            if (new_cf_url) {
                if (!new_cf_url.endsWith("/")) {
                    new_cf_url = new_cf_url + '/';
                }

                use_url = new_cf_url;
            }
        }

        if (!is_commits) {
            if (open_mix_model) {
                mixCloneButton();
            } else {
                addCloneButton();
            }
            addSiteButton();
        }

        addReleaseButton();
        addReleaseSiteButton();
    }

    //融合到本有的clone按钮
    function mixCloneButton() {
        let https_template = `
        <p class="mb-2 mt-2 get-repo-decription-text">
                            CNPMJS.ORG加速通道
                        </p>
                        <div class="input-group mb-2">
                            <input type="text" class="form-control input-monospace input-sm" data-autoselect="" value="${cnpmjs_url}/${github_auth_name}/${git_name}.git" readonly="">
                            <div class="input-group-button">
                                <clipboard-copy value="${cnpmjs_url}/${github_auth_name}/${git_name}.git" class="btn btn-sm" tabindex="0" role="button"><svg class="octicon octicon-clippy" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true">
                                        <path fill-rule="evenodd" d="M2 13h4v1H2v-1zm5-6H2v1h5V7zm2 3V8l-3 3 3 3v-2h5v-2H9zM4.5 9H2v1h2.5V9zM2 12h2.5v-1H2v1zm9 1h1v2c-.02.28-.11.52-.3.7-.19.18-.42.28-.7.3H1c-.55 0-1-.45-1-1V4c0-.55.45-1 1-1h3c0-1.11.89-2 2-2 1.11 0 2 .89 2 2h3c.55 0 1 .45 1 1v5h-1V6H1v9h10v-2zM2 5h8c0-.55-.45-1-1-1H8c-.55 0-1-.45-1-1s-.45-1-1-1-1 .45-1 1-.45 1-1 1H3c-.55 0-1 .45-1 1z"></path>
                                    </svg>
                                </clipboard-copy>
                            </div>
                        </div>
                        <p class="mb-2 get-repo-decription-text">
                            <a href="https://fastgit.org" target="_blank">FastGit.ORG</a>加速通道
                        </p>
                        <div class="input-group mb-2">
                            <input type="text" class="form-control input-monospace input-sm" data-autoselect="" value="${fastgithub_url}/${github_auth_name}/${git_name}.git" readonly="">
                            <div class="input-group-button">
                                <clipboard-copy value="${fastgithub_url}/${github_auth_name}/${git_name}.git" class="btn btn-sm" tabindex="0" role="button"><svg class="octicon octicon-clippy" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true">
                                        <path fill-rule="evenodd" d="M2 13h4v1H2v-1zm5-6H2v1h5V7zm2 3V8l-3 3 3 3v-2h5v-2H9zM4.5 9H2v1h2.5V9zM2 12h2.5v-1H2v1zm9 1h1v2c-.02.28-.11.52-.3.7-.19.18-.42.28-.7.3H1c-.55 0-1-.45-1-1V4c0-.55.45-1 1-1h3c0-1.11.89-2 2-2 1.11 0 2 .89 2 2h3c.55 0 1 .45 1 1v5h-1V6H1v9h10v-2zM2 5h8c0-.55-.45-1-1-1H8c-.55 0-1-.45-1-1s-.45-1-1-1-1 .45-1 1-.45 1-1 1H3c-.55 0-1 .45-1 1z"></path>
                                    </svg>
                                </clipboard-copy>
                            </div>
                        </div>
                        <p class="mb-2 get-repo-decription-text">
                            Cloudflare Workers加速通道
                        </p>
                        <div class="input-group">
                            <input type="text" class="form-control input-monospace input-sm" data-autoselect="" value="${use_url}/https://github.com/${github_auth_name}/${git_name}.git" readonly="">
                            <div class="input-group-button">
                                <clipboard-copy value="${use_url}/https://github.com/${github_auth_name}/${git_name}.git" class="btn btn-sm" tabindex="0" role="button"><svg class="octicon octicon-clippy" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true">
                                    <path fill-rule="evenodd" d="M2 13h4v1H2v-1zm5-6H2v1h5V7zm2 3V8l-3 3 3 3v-2h5v-2H9zM4.5 9H2v1h2.5V9zM2 12h2.5v-1H2v1zm9 1h1v2c-.02.28-.11.52-.3.7-.19.18-.42.28-.7.3H1c-.55 0-1-.45-1-1V4c0-.55.45-1 1-1h3c0-1.11.89-2 2-2 1.11 0 2 .89 2 2h3c.55 0 1 .45 1 1v5h-1V6H1v9h10v-2zM2 5h8c0-.55-.45-1-1-1H8c-.55 0-1-.45-1-1s-.45-1-1-1-1 .45-1 1-.45 1-1 1H3c-.55 0-1 .45-1 1z"></path>
                                </svg>
                                </clipboard-copy>
                            </div>
                        </div>
                        <div class="mt-2 d-flex" style="text-align:center;">
                            <a class="flex-1 btn btn-outline get-repo-btn " rel="nofollow" href="${use_url}/https://github.com/${github_auth_name}/${git_name}/archive/master.zip">
                            加速下载ZIP
                            </a>
                        </div>
                        ${close_yidian ? '' : yidian}
        `;

        $("div[role='tabpanel']").eq(0).append(https_template);

        let ssh_template = `
        <p class="mb-2 mt-2 get-repo-decription-text">
        <a href="https://github.zhlh6.cn/" target="_blank">github.zhlh6.cn</a>加速通道
        <span>
            <a href="https://github.com/fhefh2015/Fast-GitHub" target="_blank">设置教程</a>
        </span>
    </p>
    <div class="input-group">
        <input type="text" class="form-control input-monospace input-sm" data-autoselect="" value="${ssh_url}${github_auth_name}/${git_name}.git" readonly="">
        <div class="input-group-button">
            <clipboard-copy value="${ssh_url}${github_auth_name}/${git_name}.git" class="btn btn-sm" tabindex="0" role="button"><svg class="octicon octicon-clippy" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true">
                <path fill-rule="evenodd" d="M2 13h4v1H2v-1zm5-6H2v1h5V7zm2 3V8l-3 3 3 3v-2h5v-2H9zM4.5 9H2v1h2.5V9zM2 12h2.5v-1H2v1zm9 1h1v2c-.02.28-.11.52-.3.7-.19.18-.42.28-.7.3H1c-.55 0-1-.45-1-1V4c0-.55.45-1 1-1h3c0-1.11.89-2 2-2 1.11 0 2 .89 2 2h3c.55 0 1 .45 1 1v5h-1V6H1v9h10v-2zM2 5h8c0-.55-.45-1-1-1H8c-.55 0-1-.45-1-1s-.45-1-1-1-1 .45-1 1-.45 1-1 1H3c-.55 0-1 .45-1 1z"></path>
            </svg>
            </clipboard-copy>
        </div>
    </div>
    <div class="mt-2 d-flex" style="text-align:center;">
        <a class="flex-1 btn btn-outline get-repo-btn " rel="nofollow" href="${use_url}/https://github.com/${github_auth_name}/${git_name}/archive/master.zip">
            加速下载ZIP
        </a>
    </div>
    ${close_yidian ? '' : yidian}
        `;

        $("div[role='tabpanel']").eq(1).append(ssh_template);

        //     let button = `
        //     <li class="Box-row Box-row--hover-gray p-0"><a class="d-flex flex-items-center text-gray-dark text-bold no-underline p-3" rel="nofollow" href="${use_url}/https://github.com/${github_auth_name}/${git_name}/archive/master.zip"><svg class="octicon octicon-file-zip mr-3" viewbox="0 0 16 16" version="1.1" width="16" height="16" aria-hidden="true">
        //         <path fill-rule="evenodd" d="M3.5 1.75a.25.25 0 01.25-.25h3a.75.75 0 000 1.5h.5a.75.75 0 000-1.5h2.086a.25.25 0 01.177.073l2.914 2.914a.25.25 0 01.073.177v8.586a.25.25 0 01-.25.25h-.5a.75.75 0 000 1.5h.5A1.75 1.75 0 0014 13.25V4.664c0-.464-.184-.909-.513-1.237L10.573.513A1.75 1.75 0 009.336 0H3.75A1.75 1.75 0 002 1.75v11.5c0 .649.353 1.214.874 1.515a.75.75 0 10.752-1.298.25.25 0 01-.126-.217V1.75zM8.75 3a.75.75 0 000 1.5h.5a.75.75 0 000-1.5h-.5zM6 5.25a.75.75 0 01.75-.75h.5a.75.75 0 010 1.5h-.5A.75.75 0 016 5.25zm2 1.5A.75.75 0 018.75 6h.5a.75.75 0 010 1.5h-.5A.75.75 0 018 6.75zm-1.25.75a.75.75 0 000 1.5h.5a.75.75 0 000-1.5h-.5zM8 9.75A.75.75 0 018.75 9h.5a.75.75 0 010 1.5h-.5A.75.75 0 018 9.75zm-.75.75a1.75 1.75 0 00-1.75 1.75v3c0 .414.336.75.75.75h2.5a.75.75 0 00.75-.75v-3a1.75 1.75 0 00-1.75-1.75h-.5zM7 12.25a.25.25 0 01.25-.25h.5a.25.25 0 01.25.25v2.25H7v-2.25z"></path>
        //     </svg>
        //     下载ZIP打包文件
        // </a></li>
        //     `;


    }


    //添加进入镜像站点按钮
    function addSiteButton() {
        let template = `
    <details class="get-repo-select-menu js-get-repo-select-menu  position-relative details-overlay details-reset">
        <summary class="btn ml-2 btn-primary">
            <a href="${cnpmjs_url}/${github_auth_name}/${git_name}" style="color:#fff;">
               镜像
            </a>
        </summary>  
    </details>
    `;
        $(".file-navigation").append(template);
    }

    //添加克隆按钮
    function addCloneButton() {
        let template = `<span class="d-flex">
    <details class="get-repo-select-menu js-get-repo-select-menu  position-relative details-overlay details-reset">
        <summary class="btn ml-2 btn-primary">
            加速
            <span class="dropdown-caret"></span>
        </summary>
        <div class="position-relative">
            <div class="get-repo-modal dropdown-menu dropdown-menu-sw pb-0 js-toggler-container js-get-repo-modal p-3" style="width:352px;">
                <div class="get-repo-modal-options">
                    <div class="clone-options https-clone-options">
                        <h4 class="mb-1">
                            使用HTTPS克隆
                            <a class="muted-link" href="https://docs.github.com/cn/github/using-git/which-remote-url-should-i-use" target="_blank" title="Which remote URL should I use?">
                                <svg class="octicon octicon-question" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true">
                                    <path fill-rule="evenodd" d="M6 10h2v2H6v-2zm4-3.5C10 8.64 8 9 8 9H6c0-.55.45-1 1-1h.5c.28 0 .5-.22.5-.5v-1c0-.28-.22-.5-.5-.5h-1c-.28 0-.5.22-.5.5V7H4c0-1.5 1.5-3 3-3s3 1 3 2.5zM7 2.3c3.14 0 5.7 2.56 5.7 5.7s-2.56 5.7-5.7 5.7A5.71 5.71 0 011.3 8c0-3.14 2.56-5.7 5.7-5.7zM7 1C3.14 1 0 4.14 0 8s3.14 7 7 7 7-3.14 7-7-3.14-7-7-7z"></path>
                                </svg>
                            </a>
                        </h4>
                        <p class="mb-2 get-repo-decription-text">
                            CNPMJS.ORG加速通道
                        </p>
                        <div class="input-group mb-2">
                            <input type="text" class="form-control input-monospace input-sm" data-autoselect="" value="${cnpmjs_url}/${github_auth_name}/${git_name}.git" readonly="">
                            <div class="input-group-button">
                                <clipboard-copy value="${cnpmjs_url}/${github_auth_name}/${git_name}.git" class="btn btn-sm" tabindex="0" role="button"><svg class="octicon octicon-clippy" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true">
                                        <path fill-rule="evenodd" d="M2 13h4v1H2v-1zm5-6H2v1h5V7zm2 3V8l-3 3 3 3v-2h5v-2H9zM4.5 9H2v1h2.5V9zM2 12h2.5v-1H2v1zm9 1h1v2c-.02.28-.11.52-.3.7-.19.18-.42.28-.7.3H1c-.55 0-1-.45-1-1V4c0-.55.45-1 1-1h3c0-1.11.89-2 2-2 1.11 0 2 .89 2 2h3c.55 0 1 .45 1 1v5h-1V6H1v9h10v-2zM2 5h8c0-.55-.45-1-1-1H8c-.55 0-1-.45-1-1s-.45-1-1-1-1 .45-1 1-.45 1-1 1H3c-.55 0-1 .45-1 1z"></path>
                                    </svg>
                                </clipboard-copy>
                            </div>
                        </div>
                        <p class="mb-2 get-repo-decription-text">
                            <a href="https://fastgit.org" target="_blank">FastGit.ORG</a>加速通道
                        </p>
                        <div class="input-group mb-2">
                            <input type="text" class="form-control input-monospace input-sm" data-autoselect="" value="${fastgithub_url}/${github_auth_name}/${git_name}.git" readonly="">
                            <div class="input-group-button">
                                <clipboard-copy value="${fastgithub_url}/${github_auth_name}/${git_name}.git" class="btn btn-sm" tabindex="0" role="button"><svg class="octicon octicon-clippy" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true">
                                        <path fill-rule="evenodd" d="M2 13h4v1H2v-1zm5-6H2v1h5V7zm2 3V8l-3 3 3 3v-2h5v-2H9zM4.5 9H2v1h2.5V9zM2 12h2.5v-1H2v1zm9 1h1v2c-.02.28-.11.52-.3.7-.19.18-.42.28-.7.3H1c-.55 0-1-.45-1-1V4c0-.55.45-1 1-1h3c0-1.11.89-2 2-2 1.11 0 2 .89 2 2h3c.55 0 1 .45 1 1v5h-1V6H1v9h10v-2zM2 5h8c0-.55-.45-1-1-1H8c-.55 0-1-.45-1-1s-.45-1-1-1-1 .45-1 1-.45 1-1 1H3c-.55 0-1 .45-1 1z"></path>
                                    </svg>
                                </clipboard-copy>
                            </div>
                        </div>
                        <p class="mb-2 get-repo-decription-text">
                            Cloudflare Workers加速通道
                        </p>
                        <div class="input-group">
                            <input type="text" class="form-control input-monospace input-sm" data-autoselect="" value="${use_url}/https://github.com/${github_auth_name}/${git_name}.git" readonly="">
                            <div class="input-group-button">
                                <clipboard-copy value="${use_url}/https://github.com/${github_auth_name}/${git_name}.git" class="btn btn-sm" tabindex="0" role="button"><svg class="octicon octicon-clippy" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true">
                                    <path fill-rule="evenodd" d="M2 13h4v1H2v-1zm5-6H2v1h5V7zm2 3V8l-3 3 3 3v-2h5v-2H9zM4.5 9H2v1h2.5V9zM2 12h2.5v-1H2v1zm9 1h1v2c-.02.28-.11.52-.3.7-.19.18-.42.28-.7.3H1c-.55 0-1-.45-1-1V4c0-.55.45-1 1-1h3c0-1.11.89-2 2-2 1.11 0 2 .89 2 2h3c.55 0 1 .45 1 1v5h-1V6H1v9h10v-2zM2 5h8c0-.55-.45-1-1-1H8c-.55 0-1-.45-1-1s-.45-1-1-1-1 .45-1 1-.45 1-1 1H3c-.55 0-1 .45-1 1z"></path>
                                </svg>
                                </clipboard-copy>
                            </div>
                        </div>
                        <h4 class="mb-1" style="margin-top:10px;">
                        使用SSH克隆
                            <a class="muted-link" href="https://docs.github.com/cn/github/using-git/which-remote-url-should-i-use" target="_blank" title="Which remote URL should I use?">
                                <svg class="octicon octicon-question" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true">
                                    <path fill-rule="evenodd" d="M6 10h2v2H6v-2zm4-3.5C10 8.64 8 9 8 9H6c0-.55.45-1 1-1h.5c.28 0 .5-.22.5-.5v-1c0-.28-.22-.5-.5-.5h-1c-.28 0-.5.22-.5.5V7H4c0-1.5 1.5-3 3-3s3 1 3 2.5zM7 2.3c3.14 0 5.7 2.56 5.7 5.7s-2.56 5.7-5.7 5.7A5.71 5.71 0 011.3 8c0-3.14 2.56-5.7 5.7-5.7zM7 1C3.14 1 0 4.14 0 8s3.14 7 7 7 7-3.14 7-7-3.14-7-7-7z"></path>
                                </svg>
                            </a>
                        </h4>
                        <p class="mb-2 get-repo-decription-text">
                            <a href="https://github.zhlh6.cn/" target="_blank">github.zhlh6.cn</a>加速通道
                            <span>
                                <a href="https://github.com/fhefh2015/Fast-GitHub" target="_blank">设置教程</a>
                            </span>
                        </p>
                        <div class="input-group">
                            <input type="text" class="form-control input-monospace input-sm" data-autoselect="" value="${ssh_url}${github_auth_name}/${git_name}.git" readonly="">
                            <div class="input-group-button">
                                <clipboard-copy value="${ssh_url}${github_auth_name}/${git_name}.git" class="btn btn-sm" tabindex="0" role="button"><svg class="octicon octicon-clippy" viewBox="0 0 14 16" version="1.1" width="14" height="16" aria-hidden="true">
                                    <path fill-rule="evenodd" d="M2 13h4v1H2v-1zm5-6H2v1h5V7zm2 3V8l-3 3 3 3v-2h5v-2H9zM4.5 9H2v1h2.5V9zM2 12h2.5v-1H2v1zm9 1h1v2c-.02.28-.11.52-.3.7-.19.18-.42.28-.7.3H1c-.55 0-1-.45-1-1V4c0-.55.45-1 1-1h3c0-1.11.89-2 2-2 1.11 0 2 .89 2 2h3c.55 0 1 .45 1 1v5h-1V6H1v9h10v-2zM2 5h8c0-.55-.45-1-1-1H8c-.55 0-1-.45-1-1s-.45-1-1-1-1 .45-1 1-.45 1-1 1H3c-.55 0-1 .45-1 1z"></path>
                                </svg>
                                </clipboard-copy>
                            </div>
                        </div>
                    </div>
                    <div class="mt-2 d-flex" style="text-align:center;">
                        <a class="flex-1 btn btn-outline get-repo-btn " rel="nofollow" href="${use_url}/https://github.com/${github_auth_name}/${git_name}/archive/master.zip">
                        加速下载ZIP
                        </a>
                    </div>
                    ${close_yidian ? '' : yidian}
                </div>
            </div>
        </div>
    </details>
</span>`;
        $(".file-navigation").append(template);
    }

    //添加release页面按钮
    function addReleaseButton() {

        if (url.indexOf("releases/tag") != -1) {

            $(".release .d-flex").css({
                'padding': '5px 0',
            });

            $(".release .d-flex small").css({
                "width": "20%",
                "text-align": "right"
            });

            $('.release').each(function () {
                if ($(this).find('.Box-body>a').length) {

                    $(this).find('.Box-body>a').each(function () {

                        $(this).css({
                            'width': "50%",
                            'overflow': "hidden"
                        });

                        let href = $(this).attr('href');
                        href = 'https://github.com' + href;

                        let span = `
                        <a class="btn btn-outline get-repo-btn" rel="nofollow" href="${use_url + '/' + href}">快速下载</a>
                    `;
                        $(this).after(span);
                    });
                }

                // if ($(this).find('.d-block.Box-body>a').length) {

                //     $(this).find('.d-block.Box-body>a').each(function () {
                //         let href = $(this).attr('href');
                //         href = 'https://github.com' + href;

                //         var span = `<a class="btn btn-outline get-repo-btn mt2" rel="nofollow" href="${use_url + '/' + href}">快速下载</a>`;
                //         $(this).after(span);
                //     });
                // }

            });

        }


        if ($(".release-entry").length) {

            if ($('.release-entry').find('.d-flex.Box-body>a').length || $('.release-entry').find('.d-block.Box-body>a').length) {

                $(".release-entry .d-flex").css({
                    'padding': '5px 0'
                });

                $('.release-entry').each(function () {
                    if ($(this).find('.d-flex.Box-body>a').length) {

                        $(this).find('.d-flex.Box-body>a').each(function () {

                            let href = $(this).attr('href');
                            href = 'https://github.com' + href;

                            let span = `
                        <div style=" position: absolute;left: 65%;">
                            <a class="flex-1 btn btn-outline get-repo-btn" rel="nofollow" href="${use_url + '/' + href}">快速下载</a>
                        </div>
                        `;
                            $(this).after(span);
                        });
                    }

                    if ($(this).find('.d-block.Box-body>a').length) {

                        $(this).find('.d-block.Box-body>a').each(function () {
                            let href = $(this).attr('href');
                            href = 'https://github.com' + href;

                            var span = `<a class="btn btn-outline get-repo-btn mt2" rel="nofollow" href="${use_url + '/' + href}">快速下载</a>`;
                            $(this).after(span);
                        });
                    }

                    console.log("not found");

                });
            }

            if ($(".release-entry .list-style-none").length) {

                $('.release-entry').each(function () {

                    $(this).find('.list-style-none').each(function () {
                        $(this).find('.muted-link').each(function (key, elem) {

                            if (parseInt($(elem).attr('data-create'))) {
                                return false;
                            }

                            if (parseInt(key) != 0) {

                                let href = $(elem).attr('href');
                                let href_text = $(elem).text();
                                let download_href = `${use_url}/https://github.com${href}`;
                                let template = `
                            <a class="muted-link" style="margin-left:4px;" href="${download_href}" rel="nofollow">
                                <svg class="octicon octicon-file-zip" viewBox="0 0 12 16" version="1.1" width="12" height="16" aria-hidden="true">
                                    <path fill-rule="evenodd" d="M8.5 1H1a1 1 0 00-1 1v12a1 1 0 001 1h10a1 1 0 001-1V4.5L8.5 1zM11 14H1V2h3v1h1V2h3l3 3v9zM5 4V3h1v1H5zM4 4h1v1H4V4zm1 2V5h1v1H5zM4 6h1v1H4V6zm1 2V7h1v1H5zM4 9.28A2 2 0 003 11v1h4v-1a2 2 0 00-2-2V8H4v1.28zM6 10v1H4v-1h2z">
                                    </path>
                                </svg>
                                加速${href_text}
                            </a>
                            `;

                                $(this).parent('.d-inline-block').append(template);
                                $(elem).attr('data-create', 1);

                            } else {
                                $(elem).attr('data-create', 1);
                            }
                        });

                    });

                    // if ($(this).find('.list-style-none').length) {


                    // }
                });
            }
        }
    }

    //添加release页面镜像站点按钮
    function addReleaseSiteButton() {
        if ($(".subnav-links").length) {
            let template = `
            <a class="js-selected-navigation-item selected subnav-item" href="${cnpmjs_url}${pathname}">
                进入加速Release页面
            </a>
        `;

            $(".subnav-links").append(template);
        }
    }
})()