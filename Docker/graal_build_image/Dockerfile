FROM centos:7

RUN yum -y install wget
RUN mkdir -p /opt/{download,graal,maven,project}

#GraalVM
RUN wget -P /opt/download/ https://github.com/oracle/graal/releases/download/vm-1.0.0-rc1/graalvm-ce-1.0.0-rc1-linux-amd64.tar.gz
RUN tar -xf /opt/download/graalvm-ce-1.0.0-rc1-linux-amd64.tar.gz -C /opt/graal --strip 1
ENV PATH="/opt/graal/bin:${PATH}"
ENV JAVA_HOME="/opt/graal"

# GraalVM native-image prerequisites
RUN yum -y glibc-devel; yum -y install zlib-devel; yum -y install gcc

# Maven
RUN wget -P /opt/download/ http://apache.40b.nl/maven/maven-3/3.5.3/binaries/apache-maven-3.5.3-bin.tar.gz
RUN tar -xf /opt/download/apache-maven-3.5.3-bin.tar.gz -C /opt/maven --strip 1
ENV PATH="/opt/maven/bin:${PATH}"
ENV M2_HOME="/opt/maven"
RUN mkdir -p /root/.m2


WORKDIR /opt/project
VOLUME ["/opt/project", "/root/.m2"]
EXPOSE 8080

# Clean up
RUN rm -rf /opt/download
RUN yum clean all