# hockey-tree [![Build Status](https://travis-ci.org/keiththompson/hockey-tree.svg?branch=master)](https://travis-ci.org/keiththompson/hockey-tree) [ ![Download](https://api.bintray.com/packages/keiththompson/keith-maven/hockey-tree/images/download.svg) ](https://bintray.com/keiththompson/keith-maven/hockey-tree/_latestVersion)

Download
--------

```groovy
compile 'com.keith:hockey-tree:<latest-version>'
```

Usage
-----------

Two simple steps

1. Plant a BreadcrumbTree
2. Register for Crashes with HockeySDK with a BreadCrumbCrashManagerListener

```java
Breadcrumbs breadcrumbs = new Breadcrumbs();
Timber.plant(new BreadcrumbTree(Log.DEBUG, breadcrumbs))
CrashManager.register(context, HOCKEY_ID, new BreadCrumbCrashManagerListener(breadcrumbs));
```

Controlling your crumbs
----------------------------------

### Adjusting the size of the breadcrumbs payload
You can adjust the amount & the maximum permitted size of Breadcrumbs sent to hockey via Breadcrumbs.
The following code is configured to send a maximum of 50 breadcrumbs, each with an approximate limit of 2048 bytes to Hockeyapp.

```java
Breadcrumbs breadcrumbs = new Breadcrumbs(new ConcurrentLinkedQueue<Breadcrumb>(), 50, 2048);
```

### Filtering what gets sent to Hockeyapp
The first parameter passed to the BreadcrumbTree directly filters what will be sent to hockeyapp. 
For example, the code below will only send logs sent with at least a `DEBUG` level priority. 

```java
  Timber.plant(new BreadcrumbTree(Log.DEBUG, breadcrumbs))
  Timber.tag("verbose").v("I won't be sent to hockeyapp");
  Timber.tag("debug").d("I will be sent to hockeyapp");
```

License
-------

```
MIT License

Copyright (c) 2017 Keith Thompson

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
