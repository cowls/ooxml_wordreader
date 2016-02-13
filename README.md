# OOXML WordReader

## Overview

This repository contains a library that is capable of reading Word Documents in OOXML format (.docx) and extracting the contents as plain text.

### Why not use Apache POI?

Apache POI is a huge project, containing 10s of thousands of classes. Unfortunately, this causes problems when you want to read word documents in android applications as the android JVM complains when loading more then ~65000 classes.

This can also be used when simpel word doc reading is required in other java apps and you only want to include a small library to manage it.

## Building OOXML Reader source

OOXML Reader uses maven, to build simply clone the repository and then run `mvn clean install`

## Including as maven dependency

Once OOXML Reader is installed to your maven repository, you can include it with this maven dependency:

```xml
<dependency>
    <groupId>com.drunkenbear.lib</groupId>
    <artifactId>ooxml-wordreader</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```
