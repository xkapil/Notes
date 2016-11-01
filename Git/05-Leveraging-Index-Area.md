### Leveraging Index Area

* ***Index*** refers to the objects (blobs) that have been newly created by running `git add` command.

* The *index* area provides us the ability to build up next commit in stages. Developers using `git commit -am <message>` really don't use this ability provided by Git.

#### Hands-on

* Let's create a new git repo.
```
mkdir repo && cd repo
git init
```
* At this moment there are no objects in the repo. Executing `find .git/objects -type f | sort` will return nothing.
* Run `ls -al` and notice that the *index* file does not exist.
* Now let's run following commands:
```
echo "Line 1" >> file.txt
git add .
```
* Running `ls -al` will tell us that a new file named *index* is created. Take a note of file size.
* Also, running `find .git/objects -type f | sort` will tell us that a new blob object has been created reprented by the hash-id
* *The above activity tells us that `git add` results into blob creation.*

* Let's add few more files to the staging/index area.
```
echo "Line 1" >> file2.txt
git add file2.txt
echo "Line 1" >> file3.txt
git add file3.txt
```
* Running `git ls-files --stage` shows up the files which are a part of index area.
* Now run `ls -al` and take a note of the *index* file. *The file size should have gone up. Because **index** *file is the place where git stages/maintains the next commit. It holds the reference to the blobs that were created during `git add`*.

* Now let's add few directories in the repo and add files in them.
```
mkdir dir1
echo "New Line in Dir1/file" >> dir1/file.txt
mkdir dir2
echo "New Line in Dir2/file" >> dir2/file.txt
```
* `git status` tells us that new directories have been added to the working directory which git isn't aware of.
* Now let's add them `git add .`
* And run `find .git/objects -type f`.
* *We notice that two new blob objects have been created for new contents, however no tree objects have been added for the directories that we added.*

* Let's do a commit now `git commit -m "M1"`
* And run `find .git/objects -type f`.
* *A tree object is only created when we perform a git commit*
* *We see that now there are four additional git objects; one is a commit object, and the rest three are tree objects for the directory that we added. One each for dir1 and dir2; and another for the root tree which is being referenced by the commit object*

* Please note that it's possible that only three new objects are created instead of four. This is possible in the situation where both tree (dir1 and dir2) have same contents/blobs. Hence instead of two different tree objects - there is only one being referenced for both dir1 and dir2.

#### git add --patch

* WIP
