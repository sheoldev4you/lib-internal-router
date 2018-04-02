# lib-internal-router

[![jitpack_badge](https://jitpack.io/v/tfSheol/lib-internal-router.svg)](https://jitpack.io/#tfSheol/lib-internal-router)
[![codebeat badge](https://codebeat.co/badges/382c5525-61ab-484c-859b-4fb9889070aa)](https://codebeat.co/projects/github-com-tfsheol-lib-internal-router-master)
[![Build Status](https://travis-ci.org/tfSheol/lib-internal-router.svg?branch=master)](https://travis-ci.org/tfSheol/lib-internal-router)
[![Sonar_maintainability_rating](https://sonarcloud.io/api/project_badges/measure?project=fr.sheol.lib%3Alib-internal-router&metric=sqale_rating)](https://sonarcloud.io/api/project_badges/measure?project=fr.sheol.lib%3Alib-internal-router&metric=sqale_rating)

[![Sonar_Coverage](https://sonarcloud.io/api/project_badges/measure?project=fr.sheol.lib%3Alib-internal-router&metric=coverage)](https://sonarcloud.io/api/project_badges/measure?project=fr.sheol.lib%3Alib-internal-router&metric=coverage)
[![Sonar_Bugs](https://sonarcloud.io/api/project_badges/measure?project=fr.sheol.lib%3Alib-internal-router&metric=bugs)](https://sonarcloud.io/api/project_badges/measure?project=fr.sheol.lib%3Alib-internal-router&metric=bugs)
[![Sonar_Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=fr.sheol.lib%3Alib-internal-router&metric=vulnerabilities)](https://sonarcloud.io/api/project_badges/measure?project=fr.sheol.lib%3Alib-internal-router&metric=vulnerabilities)
[![Sonar_Lines](https://sonarcloud.io/api/project_badges/measure?project=fr.sheol.lib%3Alib-internal-router&metric=ncloc)](https://sonarcloud.io/api/project_badges/measure?project=fr.sheol.lib%3Alib-internal-router&metric=ncloc)

## Maven
### Repository

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>
```

### Dependency
```xml
<dependency>
  <groupId>com.github.tfSheol</groupId>
  <artifactId>lib-internal-router</artifactId>
  <version>0.0.2</version>
</dependency>
```

## Gradle
### Repository

```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

### Dependency
```
dependencies {
  compile 'com.github.tfSheol:lib-internal-router:0.0.2'
}
``` 

## Examples
See in Test folder `example.SimpleExample.java` and `example.controller.ControllerExample.java`

```java
public class SimpleExample {
  private static final Logger LOGGER = LoggerFactory.getLogger(SimpleExample.class);

  @Test
  public void simpleExample() {
    InternalRouter internalRouter = new InternalRouter()
            .withInternalRouteMapper(
                    new InternalRouteMapper()
                            .withAddPackageController("example/controller")
            );
    String result = internalRouter.resolve("test");
    if (null != result) {
      LOGGER.info("\"test\" => \"{}\"", result);
    }

    result = internalRouter.resolve("simple regex test : bla");
    if (null != result) {
      LOGGER.info("\"simple regex test : bla\" => \"{}\"", result);
    }

    Integer numberResult = internalRouter.resolve("simple regex number test : 42");
    if (null != numberResult) {
      LOGGER.info("\"simple regex number test : 42\" => \"{}\"", numberResult);
    }

    ObjectExample objectExample = internalRouter.resolve("power fire level 10 with WATER");
    if (null != objectExample) {
      LOGGER.info("\"power fire level 10 with WATER\" => \"{} / {} / {}\"",
              objectExample.getPower(), objectExample.getLevel(), objectExample.getAddon());
    }
  }
}
```

```java
@InternalController
public class ControllerExample {
  @InternalRoute("test")
  public String testRoute() {
    return "ok";
  }

  @InternalRoute("simple regex test : (.*)$")
  public String testSimpleRegex(String value) {
    return value;
  }

  @InternalRoute("simple regex number test : ([0-9]*)$")
  public int testSimpleNumberRegex(int value) {
    return value;
  }

  @InternalRoute("power ([a-z]*) level ([0-9]*) with ([A-Z]*$)")
  public ObjectExample testWithObject(String power, int level, String addon) {
    return new ObjectExample()
            .withAddon(addon)
            .withPower(power)
            .withLevel(level);
  }
}
```

Output Test :

```
[main] INFO example.SimpleExample - "test" => "ok"
[main] INFO example.SimpleExample - "simple regex test : bla" => "bla"
[main] INFO example.SimpleExample - "simple regex number test : 42" => "42"
[main] INFO example.SimpleExample - "power fire level 10 with WATER" => "fire / 10 / WATER"
```

## Regex
You can use this site for test your regex : [https://regex101.com/](https://regex101.com/)

## Todo
- [x] Lib base
- [x] Add more examples
- [ ] Add Jackson deserialization support with JSON
- [ ] Make documentation
