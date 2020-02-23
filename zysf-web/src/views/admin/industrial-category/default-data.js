/* eslint-disable */
import { renderInfo } from '@/libs/text'
export const initColumns = (that) => {
    return [
        {
            type: 'index',
            width: 60,
            align: 'center'
        },
        {
            title: 'id',
            key: 'id',
            minWidth:100,
            sortable: 'custom',
            align: 'center',
            render: renderInfo('id')
        },
        {
            title: 'parentId',
            key: 'parentId',
            minWidth:100,
            sortable: 'custom',
            align: 'center',
            render: renderInfo('parentId')
        },
        {
            title: 'name',
            key: 'name',
            minWidth:100,
            sortable: 'custom',
            align: 'center',
            render: renderInfo('name')
        },
        {
            title: 'ctime',
            key: 'ctime',
            minWidth:100,
            sortable: 'custom',
            align: 'center',
            render: renderInfo('ctime')
        },
        {
            title: 'utime',
            key: 'utime',
            minWidth:100,
            sortable: 'custom',
            align: 'center',
            render: renderInfo('utime')
        },
        {
            title: '操作',
            key: '操作',
            fixed:'right',
            width: 250,
            align: 'center',
            render: (h, params) => {
                return h('div', [
                    h('Button', {
                        props: {
                            type: 'success',
                            size: 'small'
                        },
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                                that.show(params.index, true)
                            }
                        }
                    },
                    [
                        h('Icon', {
                            props: {
                                type: 'ios-copy',
                                size: 16
                            }
                        }),
                    '查看'
                    ]),
                    h('Button', {
                        props: {
                            type: 'primary',
                            size: 'small'
                        },
                        style: {
                            marginRight: '5px'
                        },
                        on: {
                            click: () => {
                                that.show(params.index, false)
                            }
                        }
                    },
                    [
                        h('Icon', {
                            props: {
                                type: 'ios-create',
                                size: 16
                            }
                        }),
                        '修改'
                    ]),
                    h('Button', {
                        props: {
                            type: 'warning',
                            size: 'small'
                        },
                        on: {
                            click: () => {
                                that.delete(params.index)
                            }
                        }
                    },
                    [
                        h('Icon', {
                            props: {
                                type: 'ios-trash',
                                size: 16
                            }
                        }),
                        ' 删除'
                    ])
                ]);
            }
        }
    ]
}
