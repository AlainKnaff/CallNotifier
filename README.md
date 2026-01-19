# CallNotifier

This notifies you via mail when somebody calls your mobile.

It is useful if for some reasons you have 2 mobiles, one of which you leave most often at home.

## Features and limitations

* No root required

* Applications sends mail with calling number and date as soon as somebody calls the phone

* mails are sent synchronously, no local buffering within the app. So, make sure your phone in on the network all the time. May change in a later version.

* if caller is in your contacts, no notification is sent, unless the app has "Contacts" permission

## Installation

Download and install the latest app from the [release section](https://github.com/AlainKnaff/CallNotifier/releases)

However, you may also compile it yourself:

	./gradlew build

## Setup

* Launch the application: on first launch you are prompted make this application your "Called ID and spam app".

* On settings screens, configure the mail account used to send, and the recipient to which you wish to sent the call notifications

* Click "Send Test Mail" to make sure everything is configured correctly

* As per Android's permission system, by default you only receive notifications for callers that are not in your address book. If you wish to get notifications for these too, you need to grant the application access to your contacts (long press on the app's icon, chose app info, then permissions, and allow Contacts)
