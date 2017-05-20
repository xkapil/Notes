## Ansible

* It's a configuration management system

* One can edit the hosts (which needs to be managed by Ansible) under `sudo nano /etc/ansible/hosts`. Or you can have your own `hosts` file located anywhere and referenced with option `-i` in your `ansible` commands.

* I have added the following entry into the said file:
`was-server ansible_host=10.10.11.4 ansible_user=vagrant ansible_connection=ssh`

  Have given an alias to the host - *was-server* and provided ip to be able to ssh. Here we are making an assumption that the controller machine (where ansible is installed) can do a password-less ssh into the node (was-server in this case)

  Else, there is also a provision to provide the password in the hosts file (not recommended for obvious reasons).
  One can also tell ansible to prompt for password while executing the play book by switch `--ask-sudopass`

  One more point to note here is, one can do local installation as well by adding the following line in the hosts file:
  `localhost ansible_host=localhost ansible_connection=local`

* And, then run following command from the command line:
  `ansible -m ping all`

  This will ping all the hosts configured in the *hosts* file. Instead of using `all` we can also use `<alias> i.e. was-server` in this case, or a `<group_name>` which is configured in *hosts* file.

* One can configure group of hosts in the *hosts* file like below:
```
[group-name]
host1 ansible_ssh_host=192.0.2.1
host2 ansible_ssh_host=192.0.2.2
host3 ansible_ssh_host=192.0.2.3
```

* `-m` in the above `ansible` command refers to **module name**. In this case, we are using the `ping` module.

* The `ping` module doesn't take any arguments, but we can try another command to see how we can pass arguments.

  The `shell` module lets us send a terminal command to the remote host and retrieve the results. For instance, to find out the memory usage on our host1 machine, we could use:

  `ansible -m shell -a 'free -m' host1`

* Here is a sample *yaml* which installs *git* on all the hosts.

  ```
  - hosts: all
  become: true
  tasks:
     - name: Install Package
       apt: name=nginx update_cache=true state=latest
     - name: ensure nginx is running (and enable it at boot)
       service: name=nginx state=started enabled=yes
  ```

* Run the following command to execute the above yaml
  `ansible-playbook -i hosts provision.yaml`

* WIP

### Ansible Galaxy

* Users would like to re-use their playbooks and may want to host it somewhere for sharing it with larger audience. Very similar to Docker Hub.

* Ansible Galaxy is the way to go where users can upload ***Roles*** (or modules for understanding).

* And, then they can be installed on the controller machine using the following command:
`ansible-galaxy install <username.rolename>`

* One can list all the installed *roles* by executing `ansible-galaxy list` and can remove them by saying `ansible-galaxy remove <username.rolename>`

* One doesn't always have to use Ansible Galaxy for hosting. From the example below we can see that users can host the *roles* in various places.

```
# from galaxy
 - src: yatesr.timezone

 # from GitHub
 - src: https://github.com/bennojoy/nginx

 # from GitHub, overriding the name and specifying a specific tag
 - src: https://github.com/bennojoy/nginx
   version: master
   name: nginx_role

 # from a webserver, where the role is packaged in a tar.gz
 - src: https://some.webserver.example.com/files/master.tar.gz
   name: http-role

 # from Bitbucket
 - src: git+http://bitbucket.org/willthames/git-ansible-galaxy
   version: v1.4

 # from Bitbucket, alternative syntax and caveats
 - src: http://bitbucket.org/willthames/hg-ansible-galaxy
   scm: hg

# from GitLab or other git-based scm
 - src: git@gitlab.company.com:mygroup/ansible-base.git
   scm: git
   version: 0.1.0
```

* The above code contents can be pasted into a `yml` file (the extension is mandatory), and then can be installed using the following command:
`ansible-galaxy install -r <file-path.yml>`

* Once the *roles* are installed on the controller machine, devops can leverage them in the paybooks that they write.
