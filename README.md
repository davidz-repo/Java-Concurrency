## Java Concurrency

The MCSOS manages multiple disks, multiple printers, and multiple users - all of which can be considered concurrent processes. Files can be stored on any disk and printed on any printer by any user.  The goal of the system is to exploit possible parallelism to keep the devices (disks and printers) as busy as possible.  

#### 2x Disks
Each disk has a capacity specified to the constructor. The constructor must allocate all the StringBuffers (one per sector) when the Disk is created and must not allocate any after that.

#### 3x Printers
Each printer will write data to a file named PRINTERi where i is the index of this printer.  A printer can only handle one line of text at a time.

#### 4x Users
Each user will read from a file named USERi where `i` is the index of this user.  Each USERi file will contain a series of the following three commands.  Anything between a `.save` and a `.end` is part of the data in file X.

- .save X
- .end
- .print X

Read/Write
- Only one user writing to a disk at a time.
- Multiple threads can read at a time.

The UserThread will handle saving files to disk, and it will create a new PrintJobThread to handle each print request.

#### 1x ResourceManager
A ResourceManager is responsible for allocating a resource like a Disk or a Printer to a specific Thread. Two instances: one for allocating Disks and another for allocating Printers.

#### 1x DirectoryManager
The DirectoryManager is a table that knows where files are stored on disk.
