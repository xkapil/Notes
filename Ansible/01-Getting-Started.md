## Ansible

* It's a configuration management system

* One can edit the hosts (which needs to be managed by Ansible) under `sudo nano /etc/ansible/hosts`. Or you can have your own `hosts` file located anywhere and referenced with option `-i` in your `ansible` commands.

* I have added the following entry into the said file:
`was-server ansible_ssh_host=10.10.10.9 ansible_ssh_pass=vagrant ansible_ssh_user=vagrant`

  Have given an alias to the host - *was-server* and provided ip and credentials to be able to ssh.

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
    ---
  - hosts: all
    sudo: true
    vars:
       packages: [ 'git' ]
    tasks:
       - name: Install Package
         apt: name={{ item }} state=latest
         with_items: packages
  ```

* Run the following command to execute the above yaml
  `ansible-playbook -i hosts provision.yaml`

* WIP
