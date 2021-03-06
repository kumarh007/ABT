=====================
Aakash Business Tool
=====================

**Aakash Business Tool** is a portable accounting platform on Android
intended for Accountants and Students. `Aakash
<http://aakashlabs.org>`_ is a low cost computing device/tablet for
students, the project is initiated and funded by MHRD, Govt. of
India. Aakash already runs Android 4.0 with many educational apps
developed at IIT Bombay. Please refer to `androportal
<https://github.com/androportal/>`_ for some of the apps. Aakash
Accounting provides an easy to use interface specially for
students/newbies who have just started accounting. We have tried to
make the user's experience simple and elegant. This initial version
covers basic account management, creating vouchers and reports.

It was initially derived from `GNUkhata <http://www.gnukhata.org>`_, a
web based free accounting software which is mostly based on Python
framework.

Features
  #. Managing Organizations
  #. Maintaining books of accounts
  #. Recording, cloning and editing transactions
  #. Recording of transactions under particular project
  #. Generating reports such as Ledger Accounts, Trial Balance(Net,
     Gross, Extended), Profit and Loss Account, Project Statement,
     Cash Flow and Balance Sheet
  #. Bank Reconciliation


ABTcore - a backend to Aakash Business Tool
--------------------------------------------

The entire backend has Python code base. `ABTcore
<https://github.com/androportal/ABTcore>`_ provides a backend to
**Aakash Business Tool**. On Aakash tablet, it runs in a ``chroot``
environment(your device need to be rooted for this!). More information
can be found `here
<https://github.com/androportal/ABTcore/blob/master/README.rst>`_


How to install
---------------
Simple and recommended install procedure
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  #. This process requires a working Internet connection. 
  #. If you're behind proxy, then do appropriate settings to bypass
     your proxy server. You will find proxy setting in ``Settings`` ->
     ``WiFi`` -> ``Advance Options``. Also bypass ``127.0.0.1`` proxy
     settings.  This is **not** required for direct Internet
     connections
  #. Open the browser on Aakash and visit this link:
     **http://aakashlabs.org/builds/ABT.apk**. Download and install the
     APK.
  #. Click on **ABT icon** from android's application menu and allow
     it to download ``~300MB`` image file.
  #. The download and uncompress process will take some while, so
     please be patience. When download completes, it will prompt for
     reboot. Please say ``Yes`` to reboot.
  #. After reboot, you can again visit android's application menu and
     click on **ABT icon**
  
	
Alternate installation from your system
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

  #. Connect Aakash to your system using an USB data cable.
  #. Download and extract `install.zip
     <http://aakashlabs.org/builds/install.zip>`_ on your system

  #. Extract the zip file using ::

       unzip install.zip

  #. ``cd`` to ``install`` directory ::
     
       cd install

  #. and execute ``install.sh`` ::
       
       sudo ./install.sh

  Wait for the script to copy all necessary files to Aakash. After
  successful installation the device will reboot for changes to take
  effect.

Manual installation
~~~~~~~~~~~~~~~~~~~

  #. Download compressed image to your computer from this `link
     <http://aakashlabs.org/builds/abt.tar.gz>`_. Extract it using ::
       tar -xvzf abt.tar.gz
  #. The untar process of above file will produce ``abt.img``. Copy
     ``abt.img`` to sdcard(internal or external) of your Aakash
     tablet.
  #. Then install `ABT.apk <http://aakashlabs.org/builds/ABT.apk>`_ on
     Aakash, ``shutdown`` and ``start`` Aakash to finish
     installation. Now locate **ABT icon** in your android menu to
     start using *Aakash Business Tool*

Usage 
------

This `branch` contains an Android(4.0.3, API-15) code for the User
Interface of Aakash Business Tool.

User's can clone this repo by typing ::

   git clone -b ics https://github.com/androportal/ABT.git

if you want to clone code for Android(2.2, API-8), type ::

   git clone -b froyo https://github.com/androportal/ABT.git

*note: this branch(froyo) is obsolete*

if you want to checkout all branches then type, ::

   git checkout -b ics remotes/origin/ics
   git checkout -b docs remotes/origin/docs
   git checkout -b froyo remotes/origin/froyo

Importing **ABT** as an `eclipse <http://www.eclipse.org/>`_ project
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

`Note`: we have used Eclipse version 3.7.2(Indigo), even Eclipse
4.x(Juno) is fine to work with
 
- For setting up Android SDK and AVD, please visit this `link
  <http://developer.android.com/sdk/installing/index.html>`_
- You need to configure `ADT
  <http://developer.android.com/tools/sdk/eclipse-adt.html>`_ plugin
  on eclipse to work on Android code. Please visit `Installing ADT
  <http://developer.android.com/sdk/installing/installing-adt.html>`_
  for detail installation instructions.
  
 
After cloning the require branch, start eclipse

- go-to ``File`` menu -> ``Import``
- from the ``Import`` dialog box, select ``Android``
- from ``Android`` section, select ``Existing Android Code Into
  Workspace`` and click ``Next`` button.
- you will be taken to ``Import Projects`` dialog box, click ``Browse``
  button and select the cloned repository

Important note
~~~~~~~~~~~~~~

To test Aakash Business Tool on emulator, go to

- ``ABT/src/com/gkaakash/coreconection/CoreConnection.java`` and
  change the url from ``http://127.0.0.1:7081`` to
  ``http://10.0.2.2:7081``

- ``ABT/src/com/example/gkaakash/MainActivity``, comment those line
  number which invokes ::
  
    help_popup()

  function.

- ``ABT/src/com/example/gkaakash/createOrg``, and comment below two
  line ::

    MainActivity.no_dailog = true;
    MainActivity.help_dialog.dismiss();

- ``ABT/src/com/example/gkaakash/selectOrg``, and comment line which
  says ::

     MainActivity.no_dailog = true;

Documentation
-------------

For User and developer's guide, please visit
`http://aakashlabs.org/docs/abt/index.html
<http://aakashlabs.org/app/webroot/docs/abt/index.html>`_

Documentation in raw `sphinx <http://sphinx.pocoo.org/>`_ format can
be cloned from ::

   git clone -b docs https://github.com/androportal/ABT.git

please refer ``README.rst`` on how to generate html docs

Help, bugs, feedback
--------------------

#. Users can mail their queries, feedback and suggestions at
   accounting-on-aakash@googlegroups.com
#. Developers/Contributor can raise issues at `issues
   <https://github.com/androportal/ABT/issues>`_
#. Pull requests are most welcome


License
-------

GNU GPL Version 3, 29 June 2007.

Please refer this `link <http://www.gnu.org/licenses/gpl-3.0.txt>`_
for detailed description.

All rights belong to the National Mission on
Education through ICT, MHRD, Government of India.
