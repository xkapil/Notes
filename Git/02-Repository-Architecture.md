## How Git Works

* `Git` has a 3-tree architecture. Which means apart from **Working Copy** and **Repository**, it has a **Staging Area** as well.

* We use `git add` to move files into **Staging Area** or **Index Area** and finally `git commit` to move them into the **Repository**.

### Repository Architecture

* It has a tree kind of structure with branches and leaf.
* *blob* contains file contents and take the role of leaf in a `tree`. These are immutable.
* A *blob* is named by computing the SHA1 hash-id (40 characters) of its size and contents. Hence a file with same name and same contents, will have a same hash on 2 different machines.
* The hash verifies that the blob contents never changes.
* The same contents are always represented by the same blob, no matter where it appears: across commits, across repos.
* The metadata of the blob (eg filename) is not known to the blob. The `tree` carries the metadata for the blob.
* One tree may know the contents (or blob) as filename "foo" created on X date, and another tree may know it as "bar" with a different created date.
* Trees carrying same blobs will have same hash-ids on 2 different computers.

### blob related commands

* `git hash-object <file-path>` will give you the hash id of the file, even if it isn't part of the git repo.
* Each `commit`, `tree` and `blob` has an hash id associated with it. Hash id for `commit` will be different in different machines, since it depends upon name and system date of the commit. `blob`'s hash id depends upon the contents, hence will be same on different machines.
* Git blob represents the fundamental data unit in Git. The whole system is about `blob management`.
* `git show <hash-id>` command will show information about the hash-id.
  * blob - it will show the contents. But note, NOT the filename, coz that information is not stored by blob.
  * tree - you can see the child elements of the tree. For eg, all the dirs and files contained by the dir.
  * commit - you can see the commit message, author and even the changes which were made.

* `git cat-file -p <hash-id>` will also behave exactly similar to `git show`. `-p` stands for *pretty print*.
* You can find out the type of the hash-id using the following command: `git cat-file -t <hash-id>`
* It appears, that `tree` is also immutable. I added a new directory in the repository, and `git show <hash-id of top level dir>` it still showed me the original file, without the new dir in it. Then, I did a `find .git/objects -type f | sort` and  found out a new hash-id which showed the original file along with the new dir in it.

* `git ls-tree <hash-id>` is very similar to `ls` command in unix, which will display all the containing blobs and tree along with their hash-ids. You can also do `git ls-tree HEAD`. Since, *HEAD* is nothing but a pointer to the latest commit hash-id.
* IMPORTANT - Every **commit** holds a single `tree`.

* `hash-id` is 40 character long, but can be referred by 4 or more characters, if it can uniquely point to an object.

* Decoding ***HEAD***
  * `git rev-parse HEAD` will give you the hash-id of the latest commit.
  * `git log` command which shows all the commits made, is also helpful to see the hash-id of the latest commit or HEAD.
  * `git cat-file -t HEAD` will always return you `commit`, since HEAD is just a pointer pointing to latest commit. The commit object is holdind the top-most tree of the repo.
  * `git cat-file -p HEAD` will show you the tree hash-id along with the parent commit's hash-id (if available), along with the commit message, author information etc.
