# Bypass Exchange Policies

Bypass Exchange security policies for Microsoft Outlook and Gmail.

This [Xposed](http://repo.xposed.info/) module will attempt to bypass the security policies that e-mail applications might want to enforce when you add new Exchange accounts. Currently only Microsoft Outlook and Gmail for devices running Lollipop and above are supported.

Some applications - like Gmail - apply obfuscation (with a tool like ProGuard) to prevent tampering with the code. It's possible that this module stops working for future Gmail updates if classes and/or class members receive new random names compared to the previous version.

Feel free to submit pull requests fixing bugs or version incompatibilities or adding support for new applications.

## Download

Please refer to the [releases](https://github.com/rfgamaral/BypassExchangePolicies/releases) page to download the Xposed module until the module is not made available in the [Xposed Repository](http://repo.xposed.info/module-overview).

## Changelog

Please refer to the [CHANGELOG](CHANGELOG.md) file for the full changelog details.

## Credits

 - [rovo89](http://forum.xda-developers.com/member.php?u=4419114): Xposed, a framework for changing the system/applications behaviour without touching any APKs.
 - [Shantanu Goel](https://github.com/shantanugoel): Exchange security bypass Xposed module for Nexus and AOSP Android devices.
 - [diwulechao](https://github.com/diwulechao): Exchange security bypass for Android Lollipop Xposed module.

## License

Use of this source code is governed by an MIT-style license that can be found in the [LICENSE](LICENSE) file.
