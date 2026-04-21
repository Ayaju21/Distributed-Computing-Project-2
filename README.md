# Distributed-Computing-Project-2
📖 Project Overview
Traditional Peer-to-Peer (P2P) systems often rely on a centralized directory server to map User IDs to IP addresses. This project demonstrates how to eliminate that single point of failure by using Apache Hadoop and the MapReduce framework.

By processing client metadata across a distributed cluster, the system enables scalable and fault-tolerant peer communication, integrated with custom Java networking logic.

✨ Key Features
Decentralized Directory Management: Replaced centralized servers with a distributed MapReduce processing model.

Distributed Storage: Utilizes HDFS (Hadoop Distributed File System) to manage client data with high availability.

Parallel Processing: Implemented MapReduce jobs to efficiently index and retrieve peer connection information.

Java Networking Integration: Combines Big Data processing with Java Socket programming to facilitate a decentralized chat system.

Resource Management: Managed cluster resources and task scheduling using YARN.

🛠️ Tech Stack
Language: Java

Framework: Apache Hadoop (HDFS, MapReduce, YARN)

Environment: Linux / Ubuntu

Networking: Java Sockets (TCP/IP)

Network Simulation: Cisco Packet Tracer

📂 Repository Structure
/src: Java source files for MapReduce Mappers, Reducers, and P2P Client logic.

/input: Sample datasets containing User IDs and network configurations.

/docs: Detailed technical report and architecture diagrams.

🚀 Getting Started
Prerequisites
Java JDK 8 or higher.

Hadoop 3.x installed and configured.
