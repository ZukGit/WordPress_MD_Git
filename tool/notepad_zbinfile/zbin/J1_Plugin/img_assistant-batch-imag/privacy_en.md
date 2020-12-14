# ImageAssistant(IA) Privacy Policy Notice

This privacy policy discloses how IA collects and uses personal information.
In accordance with the privacy policy guidelines of each application store, the extension only applies to obtain the minimum permissions required to implement the function, only makes necessary communications, only collects necessary information, and is explicitly stated in this privacy policy.

## What are the clear external interaction requests for the extension and what data will be collected? How to use the collected information?

1. Regularyly request the latest version information from the server.

> The extension is currently distributed through the Chrome Web Store, Microsoft Edge Sotre, and Firefox ADD-ONS. In some Chinese Extension Web Stores developed based on Chromium, updates are usually not timely. Version information helps users obtain the latest version in time.

2. Regularly pull image url regular replacement rules.

> The extension includes a function to convert the extracted image to a large-size image. This operation is implemented by matching the image url with known rules and adding the replaced image url. The extension package contains An accompanying default rule list is released with the version, the server version is superimposed with the latest modification of the expansion pack version, and users can also add custom rules on the options page.

3. Request the local address localhost: 61257 port. This request is part of the desktop client function in the early verification stage. The save function submission contains the image information such as the URL and REFERER corresponding to the actively selected image.

> This is a collection and download function currently implemented by the local client at the initial stage. The extension will automatically request this address to confirm whether the local extension is activated to determine whether to provide the corresponding collection function. Behavior, submit the URL of the selected image and the address of the reference page for further operations, the local client is currently unavailable.

4. The request to each search engine server (google, baidu, sogou), the user's active behavior, by constructing a third-party search engine search address request is used to achieve the function of searching for images and searching by keywords.

> Through the construction of major well-known search engines to query URL to achieve the function of searching for pictures and searching by keywords.

5. When extracting multiple addresses, request asynchronously or open a separate page to request the URL address provided or selected by the user.

> Obtain page data during multi-address extraction. Analyze and obtain the picture element address in the form of DOM or open the window request address and obtain the picture element address of the page.

## Need to clarify the problem.

Since the function of the extension itself is image extract and download, in order to cover the considered scenarios as fully as possible, the image is captured as comprehensively as possible for the user to choose to download. When enabled, the extension will listen to all page Internet requests It is temporarily stored in a memory variable and automatically destroyed when the page is closed or displayed as a data source when the user performs a picture extraction operation on the page.

The implementation of the above operation is mainly reflected in two aspects. The background page will use the interface provided by chrome to listen to the global http request, and analyze whether the address contains the image address feature or the returned content content-type is the image to decide whether to store the address in a tab-related variable, and execute it on the user's page Used when extracting, or automatically destroyed when the tab is closed; inspect.js works on all url addresses, the script will search the image feature address for the currently loaded html content by text regular matching, and issue it to the page All asynchronous requests return content for text regular matching to search for the special picture address of the picture, the resulting address will be stored in the page temporary variable, when the user initiates the extraction operation on the page through the ```<input />``` tag passed Go to the background page and filter page for user operation. Similarly, when the page is closed, the corresponding temporary variable will be automatically destroyed.

### What are we not doing?

We will not collect security and privacy information such as usernames, passwords, credit cards, and cookies passed on to other sites.

In the case of the current ownership of the control, the extension does not currently, and will not cooperate with any third party that relies on collecting user access information as a means of monetization.

(The description of the interactive operation data between the local client and the extension will be explained later after the desktop client is launched.)


Version: 2020/04/28

(this notice is translated from simplified chinese version via google translate)