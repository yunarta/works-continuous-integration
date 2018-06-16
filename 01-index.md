---
title: Works - Continuous Integration
permalink: index.html

---

# Continuous Integration

"At regular intervals, the process of "continuous integration" yields executable releases that grow in functionality at 
every release. ... It is through these milestones that management can measure progress and quality, and hence 
anticipate, identify, and then actively attach risks on an ongoing basis." 
-- _GradyBooch_, **Object-Oriented Analysis and Design with Applications, 2nd ed.**
as [mentioned in C2](http://wiki.c2.com/?ContinuousIntegration)

# Works - Continuous Integration

## Introduction

This CI flow and libraries are the culminations of my three years working at V-Cube Inc, where my responsibility is
to manage and foresee development of iOS and Android application with a small team of 4 mobile developers.

 
## Grand Objectives

### Application continuous delivery

The ultimate objectives of the CI is of course to have:
- Software tester to verify revisions of the application 
- Selected test users to be able to update daily to get the latest update, so the developer can get more feedback about
  usage as well as crashes 
- And when everything is ready, the CI should deliver the release version to the end users 

![continuous delivery](/assets/img/app-continuous-delivery.png)

The diagram above provides a very simple point of view of single self contained application development.

However we do know that one application will contains reusable components that can be shared with other applications.
Thus CI process of those component should be taken into consideration for the continuous delivery totality.   

### Component continuous delivery

The next objective is to have our application component is self fulfill the continuous delivery flow. 
This way our developer links the dependencies by dependencies manager rather than linking them by source.

![continuous delivery](/assets/img/cmp-continuous-delivery.png)

As shown in the diagram above, our release branch will upload its artifact to release repository while the main develop
branch will upload its artifact to snapshot repository.


### Automate verification of components integration 

With our components deliver its artifact automatically, then we need to have our system to verify whether the components
breaks any application or component that depends on them.

![continuous delivery](/assets/img/downstream-integrate.png)

At the same time to have a nightly unstable check would be beneficial as we wanted to verify whether the applications 
and components can work with any latest version of our components and as well as with OSS components.
 
![continuous delivery](/assets/img/downstream-unstable.png)

## Summary

As with introduction of new development process, things might get overwhelming for you right now. What important right 
now is we understand the importance of each objectives. For now we can even forget about how to apply this flow 
technically as the development team it self usually might get resistive with the whole idea to begin with.

I found that it is easier for the team to adopt the development process if that we firstly starts from the component continuous
delivery, as this would alleviate some of the mundane task of the developers.

### Reading Materials
